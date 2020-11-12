package com.dekoi.backend.service;

import java.util.List;



import com.dekoi.backend.models.Compra;

public interface ICompraService {
	
	public List<Compra> findAll();
	
	public Compra save(Compra compra);
	
	public Compra findById(long id);
	
	public Compra findCompraByUserId(long id);
	
	public List<Compra> findAllComprasById(long id);
	

	public List<Compra> findAllComprasByState(boolean estado);
	

	public Compra findCompraByState(boolean estado);

}
