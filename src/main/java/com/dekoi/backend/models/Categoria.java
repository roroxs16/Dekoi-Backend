package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public long id;
	
	@Column(name="nombre", nullable = false)
	@NotEmpty(message = "El nombre de la categoria no puede estar vacio")
	public String nombre;
	
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos;

	public Categoria(long id, @NotEmpty(message = "El nombre de la categoria no puede estar vacio") String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public Categoria() {
	
	}

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
