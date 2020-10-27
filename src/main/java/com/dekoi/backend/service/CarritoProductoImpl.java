package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.ICarritoProductoDao;
import com.dekoi.backend.models.CarritoProducto;

@Service
public class CarritoProductoImpl implements ICarritoProductoService{

	@Autowired
	private ICarritoProductoDao carritoProductoDao;
	
	@Override
	public List<CarritoProducto> findAll() {
		
		return carritoProductoDao.findAll();
	}

	@Override
	public List<CarritoProducto> findByCarritoId(Long id) {
	
		return carritoProductoDao.listarCarritoProductoPorIdDeCarrito(id);
	}

	@Override
	public CarritoProducto findById(Long id) {
	
		return carritoProductoDao.findById(id).orElse(null);
	}

	@Override
	public CarritoProducto save(CarritoProducto carritoProducto) {
		
		return carritoProductoDao.save(carritoProducto);
	}

	@Override
	public void delete(Long id) {
		carritoProductoDao.deleteById(id);
	}

}
