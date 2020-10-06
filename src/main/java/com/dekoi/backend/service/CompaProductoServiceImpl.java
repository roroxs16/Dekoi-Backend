package com.dekoi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.dekoi.backend.dao.ICompraProductoDao;
import com.dekoi.backend.models.CompraProducto;

public class CompaProductoServiceImpl implements ICompraProductoService{

	@Autowired
	private ICompraProductoDao compraProductoDao;
	
	@Override
	public CompraProducto createCompraProducto(CompraProducto compraProducto) {
		// TODO Auto-generated method stub
		return this.compraProductoDao.save(compraProducto);
	}

}
