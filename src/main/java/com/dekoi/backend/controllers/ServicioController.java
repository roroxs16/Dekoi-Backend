package com.dekoi.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.service.IServicioService;
import com.dekoi.backend.models.Servicio;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ServicioController {

	@Autowired
	private IServicioService servicioService;

	@GetMapping("/servicio")
	public List<Servicio> listarServicio() {
		return servicioService.findAll();
	}


	@GetMapping("/servicio/{id}")
	public ResponseEntity<?> mostrarServicio(@PathVariable Long id) {
		Servicio servicio = null;
		Map<String, Object> response = new HashMap<>();

		try {
			servicio = servicioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (servicio == null) {
			response.put("mensaje",
					"El Servicio con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/servicio")
	public ResponseEntity<?> crearServicio(@RequestBody Servicio servicio) {

		Servicio servicioNuevo = null;

		Map<String, Object> response = new HashMap<>();

		try {

			servicioNuevo = servicioService.save(servicio);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El servicio ha sido creado con exito!");
		response.put("servicio", servicioNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/servicio/{id}")
	public ResponseEntity<?> updateServicio(@RequestBody Servicio servicio, @PathVariable Long id) {

		Servicio servicioActual = servicioService.findById(id);

		Servicio servicioActualizado = null;

		Map<String, Object> response = new HashMap<>();

		if (servicioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el servicio con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			servicioActual.setNombre(servicio.getNombre());
			servicioActual.setDescripcion(servicio.getDescripcion());

			servicioActualizado = servicioService.save(servicioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el servicio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El servicio a ha sido actualizado con éxito!");
		response.put("servicio", servicioActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/servicio/{id}")
	public ResponseEntity<?> deleteServicio(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();



		try {

			servicioService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el servicio de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Servicio eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}