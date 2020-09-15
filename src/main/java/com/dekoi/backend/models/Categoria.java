package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categoria implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public long id;
	
	@Column(name="nombre", nullable = false)
	@NotEmpty(message = "El nombre de la categoria no puede estar vacio")
	public String nombre;
	
	@JsonManagedReference
	@OneToMany(cascade= {(CascadeType.ALL)},mappedBy = "categoria")
	private List<Producto> productos;

	
	
	public Categoria(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public Categoria() {
	
	}

	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(Producto producto) {
		this.productos.add(producto);
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

