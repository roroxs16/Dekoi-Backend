package com.dekoi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.ICarritoDao;
import com.dekoi.backend.models.Carrito;

@Service
public class CarritoServiceImpl implements ICarritoService {

	@Autowired
	private ICarritoDao carritoDao;
	
	@Override
	public Carrito findById(Long id) {
		// TODO Auto-generated method stub
		return carritoDao.findById(id).orElse(null);
	}

	@Override
	public Carrito save(Carrito carrito) {
		// TODO Auto-generated method stub
		return carritoDao.save(carrito);
	}

	@Override
	public void delete(Long id) {
		carritoDao.deleteById(id);
		
	}

}
