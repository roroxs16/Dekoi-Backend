package com.dekoi.backend.service;

	
import com.dekoi.backend.models.Carrito;

public interface ICarritoService {
		
	public Carrito findById(Long id);
	
	public Carrito save(Carrito carrito);
	
	public void delete(Long id);

}
