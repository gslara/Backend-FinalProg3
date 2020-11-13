package com.example.main.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "persona")
@NoArgsConstructor	//Implementamos anotaciones de Lombok para ahorrarnos código como constructores, getters y setters
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	@NonNull private String nombre;
	
	@Column(name = "apellido")
	@NonNull private String apellido;
	
	@Column(name = "dni")
	@NonNull private int dni;
	
}
