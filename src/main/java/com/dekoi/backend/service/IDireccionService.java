package com.dekoi.backend.service;

import java.util.List;

import com.dekoi.backend.models.Direccion;

public interface IDireccionService {
	
	
	public List<Direccion> findAll();
	
	public Direccion save(Direccion direccion);
	
	public Direccion findById(long id);
	
}
