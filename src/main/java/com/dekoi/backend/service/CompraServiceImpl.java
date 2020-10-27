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

}
