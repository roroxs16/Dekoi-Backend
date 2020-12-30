package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.Producto;


public interface IProductoDao extends JpaRepository<Producto, Long>{

	@Query("select p from Producto p where p.estado='Habilitado'")
	public List<Producto> findByEstado();

	@Query("select p from Producto p where p.estado='Habilitado'")
	public Page<Producto> findAllProductosByEstadoPage(Pageable pageable);
	
}
