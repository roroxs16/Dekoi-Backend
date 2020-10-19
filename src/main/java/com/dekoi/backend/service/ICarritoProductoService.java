package com.dekoi.backend.service;

import java.util.List;

import com.dekoi.backend.models.CarritoProducto;



public interface ICarritoProductoService {

	public List<CarritoProducto> findAll();
	
	public List<CarritoProducto> findByCarritoId(Long id);
	
	public CarritoProducto findById(Long id);
	
	public CarritoProducto save(CarritoProducto carritoProducto);
	
	public void delete(Long id);
//	
//	public void deleteByProductoId(Long id);
}
