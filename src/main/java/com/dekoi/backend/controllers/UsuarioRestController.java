package com.dekoi.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.dao.IRoleDao;
import com.dekoi.backend.models.Carrito;
import com.dekoi.backend.models.Role;
import com.dekoi.backend.models.Usuario;
import com.dekoi.backend.service.IRoleService;
import com.dekoi.backend.service.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired 
	private IRoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	
	@PostMapping("/usuario")
	public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		Usuario usuarioCreado = usuario;  
		
		Carrito carrito = new Carrito();
		
		List <Role> listRol = new ArrayList<Role>();
		
		Role role = roleService.findByRole("ROLE_CLIENTE");
		listRol.add(role);
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	
		try {

			carrito.setEstado(false);
			String passCoded = bCrypt.encode(usuarioCreado.getPassword());
			usuarioCreado.setPassword(passCoded);
			usuarioCreado.setCarrito(carrito);
			usuario.setRoles(listRol);
			usuarioService.save(usuarioCreado);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario se ha registrado con exito!");
		response.put("usuario", usuarioCreado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
}
