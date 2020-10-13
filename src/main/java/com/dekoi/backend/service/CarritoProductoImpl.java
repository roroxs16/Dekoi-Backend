package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dekoi.backend.dao.ICarritoProductoDao;
import com.dekoi.backend.models.CarritoProducto;

public class CarritoProductoImpl implements ICarritoProductoService{

	@Autowired
	private ICarritoProductoDao carritoProductoDao;
	
	@Override
	public List<CarritoProducto> findAll() {
		// TODO Auto-generated method stub
		return carritoProductoDao.findAll();
	}

	@Override
	public List<CarritoProducto> findByCarritoId(Long id) {
		// TODO Auto-generated method stub
		return carritoProductoDao.listarCarritoProductoPorIdDeCarrito(id);
	}

	@Override
	public CarritoProducto findById(Long id) {
		// TODO Auto-generated method stub
		return carritoProductoDao.findById(id).orElse(null);
	}

	@Override
	public CarritoProducto save(CarritoProducto carritoProducto) {
		// TODO Auto-generated method stub
		return carritoProductoDao.save(carritoProducto);
	}

	@Override
	public void delete(Long id) {
		carritoProductoDao.deleteById(id);
	}

}
