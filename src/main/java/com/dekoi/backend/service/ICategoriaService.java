package com.dekoi.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dekoi.backend.models.Categoria;

public interface ICategoriaService {

	public List<Categoria> findAll();
	
	
	public Categoria findById(Long id);
	
	public Categoria save(Categoria categoria);
	
	public void delete(Long id);
} 
