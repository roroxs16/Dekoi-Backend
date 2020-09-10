package com.dekoi.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dekoi.backend.models.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
}
