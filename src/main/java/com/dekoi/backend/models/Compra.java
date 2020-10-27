package com.dekoi.backend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Compra {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date  fechaCompra;
	
	private double valorTotal;
	
	private String codigoEnvio;
	
	private boolean estado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = true)
	@JsonIgnoreProperties({ "compras","hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
	public Compra(long id, Date fechaCompra, double valorTotal, String codigoEnvio, boolean estado) {
		
		this.id = id;
		this.fechaCompra = fechaCompra;
		this.valorTotal = valorTotal;
		this.codigoEnvio = codigoEnvio;
		this.estado = estado;
	
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Compra() {
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCodigoEnvio() {
		return codigoEnvio;
	}

	public void setCodigoEnvio(String codigoEnvio) {
		this.codigoEnvio = codigoEnvio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
	
}
