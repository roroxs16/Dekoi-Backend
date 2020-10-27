package com.dekoi.backend.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dekoi.backend.dao.IImagenDao;
import com.dekoi.backend.models.Imagen;

@Service
public class ImagenServiceImpl implements IImagenService {

	
	@Autowired
	private IImagenDao imagenDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Imagen> findAll() {
	
		return imagenDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Imagen findById(Long id) {
		// TODO Auto-generated method stub
		return imagenDao.findById(id).orElse(null);
	}

	@Override
	public Imagen save(Imagen imagen) {
		// TODO Auto-generated method stub
		return imagenDao.save(imagen);
	}

	@Override
	public void delete(Long id) {
		imagenDao.deleteById(id);
		
	}

	@Override
	public List<Imagen> findByProductId(Long id) {
		// TODO Auto-generated method stub
		return imagenDao.listarImagenPorIdDeProducto(id);
	}

}
