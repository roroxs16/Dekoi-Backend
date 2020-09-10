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


@Entity
public class Producto implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	private String nombre;
	
	@NotNull
	@Min(value = 0, message="La cantidad en stock no puede ser menor a 0")
	private int stock;
	
	@NotNull
	@NotEmpty
	private String descripcion;
	
	@NotNull
	@Min(value = 0, message= "El valor del producto no puede ser menor a 0")
	private int valorUnitario;
	
	@NotNull
	@NotEmpty(message="La imagen no puede estar vacia")
	private String imagen;
	
	@NotNull
	@NotEmpty
	private String codigoDeBarra;
	
	@ManyToOne
	@JoinColumn(name="categoria_id", nullable=false)
	private Categoria categoria;
	
	public Producto(long id, String nombre, int stock, String descripcion, int valorUnitario, String imagen,
			String codigoDeBarra) {
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.imagen = imagen;
		this.codigoDeBarra = codigoDeBarra;
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



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}



	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}



	private static final long serialVersionUID = 4837610407696210734L;

}
