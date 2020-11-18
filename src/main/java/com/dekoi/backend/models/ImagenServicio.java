package com.dekoi.backend.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ImagenServicio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String nombre;

	
	@ManyToOne
	@JoinColumn(name = "servicio_id", nullable = false)
	@JsonIgnoreProperties({ "imagenes","hibernateLazyInitializer", "handler" })
	private Servicio servicio;

	public ImagenServicio(Long id, String nombre, Servicio servicio) {
		this.id = id;
		this.nombre = nombre;
		this.servicio = servicio;
	}

	public ImagenServicio() {
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

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	private static final long serialVersionUID = 6523569663629432965L;
	
}
