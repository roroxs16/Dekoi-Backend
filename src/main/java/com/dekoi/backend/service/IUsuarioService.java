package com.dekoi.backend.service;


import com.dekoi.backend.models.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	
	public Usuario save(Usuario usuario);
	public Usuario finById(long id);
}
