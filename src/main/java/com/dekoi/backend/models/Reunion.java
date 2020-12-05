package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reuniones")
public class Reunion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTermino;

	private String codigoReunion;

	private String estado;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonIgnoreProperties({ "usuario", "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id", nullable = false)
	@JsonIgnoreProperties({ "servicio", "hibernateLazyInitializer", "handler" })
	private Servicio servicio;

	
	
	
	public Reunion(Long id, Date fechaInicio, Date fechaTermino, String codigoReunion, String estado) {
		
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.codigoReunion = codigoReunion;
		this.estado = estado;
	}

	public Reunion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoReunion() {
		return codigoReunion;
	}

	public void setCodigoReunion(String codigoReunion) {
		this.codigoReunion = codigoReunion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1685552282918544729L;

}
