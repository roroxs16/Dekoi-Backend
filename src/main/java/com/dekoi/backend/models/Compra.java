package com.dekoi.backend.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="compraProducto")
public class Compra implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaDeCompra;
	
	private double valorTotal;
	
	private boolean estado;
	
	@OneToMany(mappedBy="pk.compra")
	@Valid
	private List<CompraProducto> compraProductos = new ArrayList<>();
	
	
	@Transient
	public double getValorTotal() {
		this.valorTotal = 0D;
		List<CompraProducto> compraProductos = getCompraProductos();
		for (CompraProducto cp: compraProductos) {
			this.valorTotal+= cp.getTotalPrice();
		}
		return this.valorTotal;
	}
	
	@Transient
	public int getCantidadProductos() {
		return this.compraProductos.size();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getFechaDeCompra() {
		return fechaDeCompra;
	}

	public void setFechaDeCompra(LocalDate localDate) {
		this.fechaDeCompra = localDate;
	}

	

	public void setValorTotal() {
		this.valorTotal = getValorTotal();
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	public List<CompraProducto> getCompraProductos() {
		return compraProductos;
	}

	public void setCompraProductos(List<CompraProducto> compraProductos) {
		this.compraProductos = compraProductos;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 5736589382849380960L;

}
