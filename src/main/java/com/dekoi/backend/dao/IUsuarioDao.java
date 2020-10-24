package com.dekoi.backend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dekoi.backend.models.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.email=?1")
	public Usuario findByEmail(String email);

}
