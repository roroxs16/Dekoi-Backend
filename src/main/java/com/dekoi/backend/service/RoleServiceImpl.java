package com.dekoi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dekoi.backend.dao.IRoleDao;
import com.dekoi.backend.models.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	@Transactional(readOnly=true)
	public Role findByRole(String role) {
		// TODO Auto-generated method stub
		return roleDao.findByRole(role);
	}

}
