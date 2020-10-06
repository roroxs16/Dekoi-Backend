package com.dekoi.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.dekoi.backend.models.CompraProducto;
import com.dekoi.backend.models.CompraProductoPK;

public interface ICompraProductoDao extends CrudRepository<CompraProducto, CompraProductoPK>{

}
