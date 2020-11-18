package com.dekoi.backend.service;

import java.util.List;

import com.dekoi.backend.models.ImagenServicio;

public interface IImagenServicioService {


	public List<ImagenServicio> findAll();
		
	public ImagenServicio findById(Long id);
	
	public ImagenServicio save(ImagenServicio imagenServicio);
	
	public void delete(Long id);
	
	public List<ImagenServicio> findByServicioId(Long id);
	
}
