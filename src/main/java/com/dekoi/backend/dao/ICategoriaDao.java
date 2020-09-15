package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Categoria;

public interface ICategoriaDao extends JpaRepository<Categoria, Long>{

}
