package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.Compra;


public interface ICompraDao extends JpaRepository<Compra, Long>{

	@Query("select c from Compra c where c.usuario.id=?1")
	public Compra findCompraByUserId(long id);
	
	@Query("select c from Compra c where c.usuario.id=?1")
	public List<Compra> findAllComprasById(long id);
	
	@Query("select c from Compra c where c.estado=?1")
	public List<Compra> findAllComprasByState(boolean estado);
	
	@Query("select c from Compra c where c.estado=?1")
	public Compra findCompraByEstado(boolean estado);
}
