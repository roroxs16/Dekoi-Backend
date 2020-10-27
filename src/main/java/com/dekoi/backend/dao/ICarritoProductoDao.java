package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.CarritoProducto;


public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long>{

	
	@Query(value= "SELECT cp FROM CarritoProducto cp where cp.carrito.id=?1")
	public List<CarritoProducto> listarCarritoProductoPorIdDeCarrito(Long id);
}
