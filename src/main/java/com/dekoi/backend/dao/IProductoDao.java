package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{

}
