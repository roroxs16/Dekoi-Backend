package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.ICompraDao;
import com.dekoi.backend.models.Compra;

@Service
public class CompraServiceImpl implements ICompraService{

	@Autowired
	private ICompraDao compraDao;
	
	@Override
	public List<Compra> findAll() {
		
		return compraDao.findAll();
	}

	@Override
	public Compra save(Compra compra) {
	
		return compraDao.save(compra);
	}

	@Override
	public Compra findById(long id) {
		
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public Compra findCompraByUserId(long id) {
		// TODO Auto-generated method stub
		return compraDao.findCompraByUserId(id);
	}

	@Override
	public List<Compra> findAllComprasById(long id) {
		// TODO Auto-generated method stub
		return compraDao.findAllComprasById(id);
	}

	@Override
	public List<Compra> findAllComprasByState(boolean estado) {
		// TODO Auto-generated method stub
		return compraDao.findAllComprasByState(estado);
	}

	@Override
	public Compra findCompraByState(boolean estado) {
		// TODO Auto-generated method stub
		return compraDao.findCompraByEstado(estado);
	}

}
