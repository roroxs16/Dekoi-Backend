package com.dekoi.backend.service;

import com.dekoi.backend.models.Compra;

public interface ICompraService {

	Iterable<Compra> getAllCompras();
	
	Compra createCompra(Compra compra);
	
	void updateCompra(Compra compra);
	
}
