package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.IImagenServicioDao;
import com.dekoi.backend.models.ImagenServicio;

@Service
public class ImagenServicioServiceImpl implements IImagenServicioService {
	
	@Autowired
	private IImagenServicioDao imagenServicioDao;

	@Override
	public List<ImagenServicio> findAll() {
		// TODO Auto-generated method stub
		return imagenServicioDao.findAll();
	}

	@Override
	public ImagenServicio findById(Long id) {
		// TODO Auto-generated method stub
		return imagenServicioDao.findById(id).orElse(null);
	}

	@Override
	public ImagenServicio save(ImagenServicio imagenServicio) {
		// TODO Auto-generated method stub
		return imagenServicioDao.save(imagenServicio);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		imagenServicioDao.deleteById(id);
	}

	@Override
	public List<ImagenServicio> findByServicioId(Long id) {
		// TODO Auto-generated method stub
		return imagenServicioDao.listarImagenPorIdDeServicio(id);
	}



}
