package com.dekoi.backend.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//si su estado es true es carrito activo se puede comprar con ese carrito
	private boolean estado;
	
	private double valor;
	
	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "carrito", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<CarritoProducto> carritosProductos;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", nullable = true)
	@JsonIgnoreProperties({ "usuario","hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
	public Carrito() {
		
	}

	public Carrito(long id, boolean estado, double valor) {
	
		this.id = id;
		this.estado = estado;
		this.valor = valor;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
