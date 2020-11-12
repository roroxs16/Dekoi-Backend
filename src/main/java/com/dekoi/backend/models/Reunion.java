package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reuniones")
public class Reunion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Date fechaHora;

	private String codigoReunion;

	private boolean estado;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", nullable = true)
	@JsonIgnoreProperties({ "usuario", "hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reunion_servicio", 
      joinColumns = @JoinColumn(name = "reunion_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "servicio_id", 
      referencedColumnName = "id"))
    private List<Servicio> servicios;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getCodigoReunion() {
		return codigoReunion;
	}

	public void setCodigoReunion(String codigoReunion) {
		this.codigoReunion = codigoReunion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1685552282918544729L;

}
