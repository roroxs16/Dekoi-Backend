package com.dekoi.backend.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CompraProducto {

	@EmbeddedId
	@JsonIgnore
	private CompraProductoPK pk;

	private int cantidad;

	public CompraProducto(Compra compra, Producto producto, int cantidad) {
		pk = new CompraProductoPK();
		pk.setCompra(compra);
		pk.setProducto(producto);

		this.cantidad = cantidad;
	}

	@Transient
	public Producto getProducto() {
		return this.pk.getProducto();
	}

	@Transient
	public Double getTotalPrice() {
		return getProducto().getValorUnitario() * getCantidad();
	}

	public CompraProductoPK getPk() {
		return pk;
	}

	public void setPk(CompraProductoPK pk) {
		this.pk = pk;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
