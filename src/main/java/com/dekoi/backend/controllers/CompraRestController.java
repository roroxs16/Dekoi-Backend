package com.dekoi.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.models.CarritoProducto;
import com.dekoi.backend.service.ICarritoProductoService;
import com.dekoi.backend.service.ICarritoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CompraRestController {
	
	@Autowired
	private ICarritoService compraService;
	
	@Autowired
	private ICarritoProductoService carritoProductoService;
	
	@RequestMapping("/carrito")
	public List<CarritoProducto> listarCarritoDeCompra(Long id){
		return carritoProductoService.findByCarritoId(id);
	}

}
