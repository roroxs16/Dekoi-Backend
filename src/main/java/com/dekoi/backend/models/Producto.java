package com.dekoi.backend.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	@Min(value = 0, message = "La cantidad en stock no puede ser menor a 0")
	private int stock;

	@NotNull
	@NotEmpty
	private String descripcion;

	@NotNull
	@Min(value = 0, message = "El valor del producto no puede ser menor a 0")
	private int valorUnitario;

	@NotNull
	@NotEmpty
	private String codigoDeBarra;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;

	public Producto(long id, String nombre, int stock, String descripcion, int valorUnitario, String codigoDeBarra, Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.codigoDeBarra = codigoDeBarra;
		this.categoria = categoria;
	}

	public Producto() {

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}

	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	private static final long serialVersionUID = 4837610407696210734L;

}
