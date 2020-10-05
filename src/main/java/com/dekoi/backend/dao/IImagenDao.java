package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.Imagen;

public interface IImagenDao extends JpaRepository<Imagen, Long> {

	
	@Query(value= "SELECT i FROM Imagen i where i.producto.id=?1")
	public List<Imagen> listarImagenPorIdDeProducto(Long id);
}
