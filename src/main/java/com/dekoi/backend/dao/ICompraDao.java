package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dekoi.backend.models.Compra;

public interface ICompraDao extends JpaRepository<Compra, Long>{

}
