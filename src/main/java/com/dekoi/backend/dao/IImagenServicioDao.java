package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.ImagenServicio;

public interface IImagenServicioDao extends JpaRepository<ImagenServicio, Long> {

	@Query(value= "SELECT i FROM ImagenServicio i where i.servicio.id=?1")
	public List<ImagenServicio> listarImagenPorIdDeServicio(Long id);
	
}
