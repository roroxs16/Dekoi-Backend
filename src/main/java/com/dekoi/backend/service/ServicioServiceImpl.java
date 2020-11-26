package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dekoi.backend.dao.IServicioDao;
import com.dekoi.backend.models.Servicio;

@Service
public class ServicioServiceImpl implements IServicioService{
	
	@Autowired
	private IServicioDao servicioDao;

	@Override
	@Transactional(readOnly=true)
	public List<Servicio> findAll() {
		// TODO Auto-generated method stub
		return servicioDao.findAll();
	}

	@Override
	public Page<Servicio> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return servicioDao.findAll(pageable);
	}

	@Override
	public Servicio findById(Long id) {
		// TODO Auto-generated method stub
		return servicioDao.findById(id).orElse(null);
	}

	@Override
	public Servicio save(Servicio servicio) {
		// TODO Auto-generated method stub
		return servicioDao.save(servicio);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		servicioDao.deleteById(id);
		
	}

}
