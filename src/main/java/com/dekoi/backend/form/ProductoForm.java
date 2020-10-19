package com.dekoi.backend.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductoForm {

	@Min(value = 1)
	private int cantidad;
	@NotEmpty
	private long productoId;

	public ProductoForm(@Min(1) int cantidad, @NotEmpty long productoId) {
		
		this.cantidad = cantidad;
		this.productoId = productoId;
	}

	public Integer getQuantity() {
		return cantidad;
	}

	public void setQuantity(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public long getProductId() {
		return productoId;
	}

	public void setProductId(long productoId) {
		this.productoId = productoId;
	}

}
