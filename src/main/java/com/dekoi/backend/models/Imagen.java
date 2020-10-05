package com.dekoi.backend.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Imagen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String nombre;

	
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	@JsonIgnoreProperties({ "imagenes","hibernateLazyInitializer", "handler" })
	private Producto producto;

	public Imagen(Long id, String nombre, Producto producto) {
		this.id = id;
		this.nombre = nombre;
		this.producto = producto;
	}

	public Imagen() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7642556935339148854L;

}
