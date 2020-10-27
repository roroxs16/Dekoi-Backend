package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dekoi.backend.models.Role;


public interface IRoleDao extends CrudRepository<Role, Long> {
	
	@Query("select r from Role r where r.nombre=?1")
	public Role findByRole(String role);
}
