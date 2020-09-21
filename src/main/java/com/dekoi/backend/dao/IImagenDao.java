package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Imagen;

public interface IImagenDao extends JpaRepository<Imagen, Long> {

}
