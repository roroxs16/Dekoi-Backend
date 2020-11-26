package com.dekoi.backend.service;

import java.util.List;

import com.dekoi.backend.models.Reunion;

public interface IReunionService {

	List<Reunion> findAll();

	public Reunion findById(Long id);
	
	public List<Reunion> findByUserId(Long id);

	public Reunion save(Reunion reunion);

	public void delete(Long id);

}
