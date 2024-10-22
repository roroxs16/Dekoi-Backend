package com.dekoi.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="producto")
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


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	@JsonIgnoreProperties({ "productos" ,"hibernateLazyInitializer", "handler" })
	private Categoria categoria;

	
	@OneToMany(cascade = { (CascadeType.ALL) }, mappedBy = "producto", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "producto", "hibernateLazyInitializer", "handler" })
	private List<Imagen> imagenes;

	public Producto(long id, String nombre, int stock, String descripcion, int valorUnitario, 			Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;

		this.categoria = categoria;
	}

	public Producto() {
		this.imagenes = new ArrayList<>();
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



	public List<Imagen> getImagenes() {
		return imagenes;
	}

	

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}



	private static final long serialVersionUID = 4837610407696210734L;

}
