package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Carrito;

public interface ICarritoDao extends JpaRepository<Carrito, Long>{

}
