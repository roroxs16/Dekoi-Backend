package com.dekoi.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dekoi.backend.models.Reunion;

public interface IReunionDao extends JpaRepository<Reunion, Long>{

	@Query("select r from Reunion r where r.usuario.id=?1")
	public List<Reunion> findReunionesByUserId(long id);
}
