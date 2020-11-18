package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Servicio;

public interface IServicioDao extends JpaRepository<Servicio, Long>{

}
