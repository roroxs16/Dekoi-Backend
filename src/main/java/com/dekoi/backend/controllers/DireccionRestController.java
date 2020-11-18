package com.dekoi.backend.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.models.Compra;
import com.dekoi.backend.models.Direccion;
import com.dekoi.backend.service.ICompraService;
import com.dekoi.backend.service.IDireccionService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class DireccionRestController {

	
	@Autowired
	private IDireccionService direccionService;


	@Autowired
	private ICompraService compraService;
	
	@PostMapping("/direccion")
	public ResponseEntity<?> crearDireccion(@RequestBody Direccion direccion, Principal principal) {

		Direccion direccionNueva = direccion;

				
		Map<String, Object> response = new HashMap<>();

		Compra searchCompra = new Compra();
		

		try {
			
			direccionService.save(direccionNueva);
			searchCompra.setDireccion(direccionNueva);
			compraService.save(searchCompra);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El la direccion ha sido creado con exito!");
		response.put("direccion", direccionNueva);
		response.put("compra", searchCompra);


		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
}
