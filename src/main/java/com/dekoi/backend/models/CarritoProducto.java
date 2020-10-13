package com.dekoi.backend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CarritoProducto {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "carrito_id", nullable = false)
	@JsonIgnoreProperties({ "carritosProductos" ,"hibernateLazyInitializer", "handler" })
	private Carrito carrito;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "producto_id", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Producto producto;
	
	public CarritoProducto(long id, int cantidad, Carrito carrito, Producto producto) {
		
		this.id = id;
		this.cantidad = cantidad;
		this.carrito = carrito;
		this.producto = producto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
