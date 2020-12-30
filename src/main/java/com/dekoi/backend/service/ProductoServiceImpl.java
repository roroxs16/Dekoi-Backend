package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.IProductoDao;
import com.dekoi.backend.models.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;

	@Override
	public List<Producto> findAll() {

		return productoDao.findByEstado();
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {

		return productoDao.findAllProductosByEstadoPage(pageable);
	}

	@Override
	public Producto findById(Long id) {

		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto save(Producto producto) {

		return productoDao.save(producto);
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			productoDao.deleteById(id);
		}

	}

}
