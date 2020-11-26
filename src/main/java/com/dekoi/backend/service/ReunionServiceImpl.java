package com.dekoi.backend.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dekoi.backend.dao.IReunionDao;
import com.dekoi.backend.models.Reunion;

@Service
public class ReunionServiceImpl implements IReunionService{

	@Autowired
	private IReunionDao reunionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Reunion> findAll() {
		return reunionDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Reunion findById(Long id) {
		return reunionDao.findById(id).orElse(null);
	}

	@Override
	public Reunion save(Reunion reunion) {
		return reunionDao.save(reunion);
	}

	@Override
	public void delete(Long id) {
		reunionDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Reunion> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return reunionDao.findReunionesByUserId(id);
	}

}
