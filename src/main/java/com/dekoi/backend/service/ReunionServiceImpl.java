package com.dekoi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dekoi.backend.dao.IReunionDao;
import com.dekoi.backend.models.Reunion;

@Service
public class ReunionServiceImpl implements IReunionService{

	@Autowired
	private IReunionDao reunionDao;
	
	@Override
	public List<Reunion> findAll() {
		// TODO Auto-generated method stub
		return reunionDao.findAll();
	}

	@Override
	public Reunion findById(Long id) {
		// TODO Auto-generated method stub
		return reunionDao.findById(id).orElse(null);
	}

	@Override
	public Reunion save(Reunion reunion) {
		// TODO Auto-generated method stub
		return reunionDao.save(reunion);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		reunionDao.deleteById(id);
	}

	@Override
	public List<Reunion> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return reunionDao.findReunionesByUserId(id);
	}

}
