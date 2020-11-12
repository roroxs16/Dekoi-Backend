package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.IDireccionDao;
import com.dekoi.backend.models.Direccion;

@Service
public class DireccionServiceImpl implements IDireccionService {

	@Autowired
	private IDireccionDao direccionDao;
	
	@Override
	public List<Direccion> findAll() {
		// TODO Auto-generated method stub
		return direccionDao.findAll();
	}

	@Override
	public Direccion save(Direccion direccion) {
		// TODO Auto-generated method stub
		return direccionDao.save(direccion);
	}

	@Override
	public Direccion findById(long id) {
		// TODO Auto-generated method stub
		return direccionDao.findById(id).orElse(null);
	}

}
