package com.dekoi.backend.service;

import java.util.List;

import com.dekoi.backend.models.Imagen;

public interface IImagenService {


	public List<Imagen> findAll();
		
	public Imagen findById(Long id);
	
	public Imagen save(Imagen imagen);
	
	public void delete(Long id);
	
	
}
