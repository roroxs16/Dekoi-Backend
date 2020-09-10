package com.dekoi.backend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dekoi.backend.service.IProductoService;
import com.dekoi.backend.service.IUploadService;

import com.dekoi.backend.models.Producto;

@CrossOrigin(origins = { "https://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IUploadService uploadService;

	@GetMapping("/producto")
	public List<Producto> listarProductos() {
		return productoService.findAll();
	}

	@GetMapping("/producto/page/{page}")
	public Page<Producto> listarProductosPaginables(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return productoService.findAll(pageable);
	}

	@GetMapping("/producto/{id}")
	public ResponseEntity<?> mostrarProducto(@PathVariable Long id) {

		Producto producto = null;

		Map<String, Object> response = new HashMap<>();

		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (producto == null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto, HttpStatus.OK);

	}

	@PostMapping("/producto")
	public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto,
			@RequestParam("archivo") MultipartFile imagen, BindingResult result) {

		Producto productoNuevo = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (!imagen.isEmpty()) {

			String nombreImagen = null;

			try {
				nombreImagen = uploadService.copiar(imagen);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del Producto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			try {
				productoNuevo.setImagen(nombreImagen);
				productoNuevo = productoService.save(producto);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		response.put("mensaje", "El producto ha sido creado con exito!");
		response.put("producto", productoNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
}
