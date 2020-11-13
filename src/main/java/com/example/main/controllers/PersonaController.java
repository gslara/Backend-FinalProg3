package com.example.main.controllers;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.dtos.PersonaDTO;
import com.example.main.services.PersonaService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping(path = "api/v1/personas")
public class PersonaController {

	private PersonaService service;

	public PersonaController(PersonaService service) {
		this.service = service;
	}
	
	
	@GetMapping("/")
	@Transactional
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Registro no encontrado.\"}");
		}
	}
	
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> getOne(@PathVariable Long id) { //PathVariable para indicar q esa variable usaremos para acceder
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Persona no encontrada.\"}");
		}
	}
	
	
	@PostMapping("/")
	@Transactional
	public ResponseEntity<?> post(@RequestBody PersonaDTO dto) { //RequestBody para indicar que la persona va en el body del request
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. No se pudo guardar la información.\"}");
		}
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> put(@PathVariable Long id, @RequestBody PersonaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. No se pudo actualizar la información.\"}");
		}
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. Persona no encontrada.\"}");
		}
	}
	
}
