package com.dekoi.backend.service;

import java.time.LocalDate;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.ICompraDao;
import com.dekoi.backend.models.Compra;

@Service
@Transactional
public class CompraServiceImpl implements ICompraService{
	
	@Autowired
	private ICompraDao compraDao;

	@Override
	public Iterable<Compra> getAllCompras() {
		
		return this.compraDao.findAll();
	}

	@Override
	public Compra createCompra(Compra compra) {
		compra.setFechaDeCompra(LocalDate.now());
		return this.compraDao.save(compra);
	}

	@Override
	public void updateCompra(Compra compra) {
		this.compraDao.save(compra);
		
	}

}
