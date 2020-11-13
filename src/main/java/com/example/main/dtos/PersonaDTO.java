package com.example.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private int dni;
	
}
