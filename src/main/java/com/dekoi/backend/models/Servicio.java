package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="servicios")
public class Servicio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	private String descripcion;
	
	
	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "servicio", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "servicio", "hibernateLazyInitializer", "handler" })
	private List<ImagenServicio> imagenes;

	
	@ManyToMany(mappedBy = "servicios")
    private List<Reunion> reuniones;
	
	
	
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



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public List<Reunion> getReuniones() {
		return reuniones;
	}



	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}


	public List<ImagenServicio> getImagenes() {
		return imagenes;
	}



	public void setImagenes(List<ImagenServicio> imagenes) {
		this.imagenes = imagenes;
	}


	private static final long serialVersionUID = 4708172819658945356L;

}
