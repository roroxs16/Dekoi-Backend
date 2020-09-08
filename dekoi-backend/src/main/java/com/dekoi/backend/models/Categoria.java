package com.dekoi.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria implements Serializable{


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public long id;
	
	@Column(name="nombre", nullable = false)
	@NotEmpty(message = "El nombre de la categoria no puede estar vacio")
	public String nombre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949641516171085666L;
	
	
}

