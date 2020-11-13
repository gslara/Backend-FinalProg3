package com.example.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.main.dtos.PersonaDTO;
import com.example.main.entities.Persona;
import com.example.main.repositories.PersonaRepository;

@Service
public class PersonaService implements BaseService<PersonaDTO>{

	private PersonaRepository repository;
	
	public PersonaService(PersonaRepository repository) { //este constructor es = @Autowired
		this.repository = repository;
	}
	
	
	//Método para encontrar todas las personas ------------------------------
	@Override
	@Transactional
	public List<PersonaDTO> findAll() throws Exception {
		try {
			List<Persona> personas = repository.findAll();
			List<PersonaDTO> dtos = new ArrayList<>();
			
			for(Persona persona : personas) {
				PersonaDTO dto = new PersonaDTO();
				
				dto.setId(persona.getId());
				dto.setNombre(persona.getNombre());
				dto.setApellido(persona.getApellido());
				dto.setDni(persona.getDni());
				
				dtos.add(dto);
			}
			return dtos;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	//Método para encontrar una persona mediante su id ----------------------
	@Override
	@Transactional
	public PersonaDTO findById(Long id) throws Exception {
		try {
			Optional<Persona> personaOptional = repository.findById(id);
			PersonaDTO dto = new PersonaDTO();
			
			Persona p = personaOptional.get();
			
			dto.setId(p.getId());
			dto.setNombre(p.getNombre());
			dto.setApellido(p.getApellido());
			dto.setDni(p.getDni());
			
			return dto; //obtiene la entidad si la encuentra
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
			
		}
	}

	
	//Método para guardar una nueva persona ---------------------------------
	@Override
	@Transactional
	public PersonaDTO save(PersonaDTO dto) throws Exception {
		try {
			if(this.validate(dto)) { //se guarda si pasa la validación
				Persona persona = new Persona();
				
				persona.setNombre(dto.getNombre().trim());
				persona.setApellido(dto.getApellido().trim());
				persona.setDni(dto.getDni());
				
				repository.save(persona);
				dto.setId(persona.getId());
			}
			
			return dto;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	//Método para actualizar los datos de una persona -----------------------
	@Override
	@Transactional
	public PersonaDTO update(Long id, PersonaDTO dto) throws Exception {
		try {
			if(this.validate(dto)) {
				Optional<Persona> personaOptional = repository.findById(id);
				Persona persona = personaOptional.get();
				
				persona.setId(id);
				persona.setNombre(dto.getNombre().trim());
				persona.setApellido(dto.getApellido().trim());
				persona.setDni(dto.getDni());
				
				repository.save(persona);
			
				dto.setId(persona.getId());
			}
			
			return dto;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	//Método para eliminar una persona --------------------------------------
	@Override
	@Transactional
	public boolean delete(Long id) throws Exception {
		try {
			if(repository.existsById(id)) {
				repository.deleteById(id);
				return true;
			
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	//Método para validar los datos -----------------------------------------
	@Transactional
	public boolean validate(PersonaDTO dto) throws Exception {
	    try {
			boolean esValido = true;
			String nombre = dto.getNombre().toLowerCase().replaceAll(" ", "");
			String apellido = dto.getApellido().toLowerCase().replaceAll(" ", "");
			String dni = Integer.toString(dto.getDni()).replaceAll(" ", "");
			
			String letras = "abcdefghijklmnñopqrstuvwxyz";
		    String numeros = "0123456789";
			
			if(nombre == null || apellido == null || dni == null) {	//comprobamos que no se hayan ingresado campos vacíos
				esValido = false;
				System.out.println("Todos los campos son obligatorios.");
			}
			
			if(nombre.length() > 20 || apellido.length() > 20) { //comprobamos que el nombre y el apellido no sean mayores a 20 caracteres
				esValido = false;
				System.out.println("Nombre o apellido mayor a 20 caracteres.");
			}
				
			if(dni.length() < 7 || dni.length() > 9) { //comprobamos que el dni tenga entre 7 y 9 caracteres
				esValido = false;
				System.out.println("DNI inválido.");
			}
			
			for(int i = 0; i < nombre.length(); i++) { //comprobamos que el nombre solo tenga letras
				if(!(letras.indexOf(nombre.charAt(i),0) != -1)) {
					esValido = false;
					System.out.println("El nombre solo puede contener letras.");
				}
			}
			
			for(var i = 0; i < apellido.length(); i++) { //comprobamos que el apellido solo tenga letras
				if(!(letras.indexOf(apellido.charAt(i),0) != -1)) {
					esValido = false;
					System.out.println("El apellido solo puede contener letras.");
				}
			}
			
			for(var i = 0; i < dni.length(); i++) { //comprobamos que el dni solo tenga números
				if(!(numeros.indexOf(dni.charAt(i),0) != -1)) {
					esValido = false;
					System.out.println("El DNI solo puede contener números.");
				}
			}
			
			return esValido;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
