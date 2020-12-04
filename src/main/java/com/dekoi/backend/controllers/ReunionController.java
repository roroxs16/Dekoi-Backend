package com.dekoi.backend.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.service.IReunionService;
import com.dekoi.backend.service.IServicioService;
import com.dekoi.backend.service.IUsuarioService;
import com.dekoi.backend.models.Reunion;
import com.dekoi.backend.models.Servicio;
import com.dekoi.backend.models.Usuario;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ReunionController {

	@Autowired
	private IUsuarioService usuarioService;


	@Autowired
	private IServicioService servicioService;

	
	@Autowired
	private IReunionService reunionService;


	@RequestMapping(value="/reunion", method= {RequestMethod.GET})
	public List<Reunion> listarTodasLasReuniones() {
		
		return reunionService.findAll();
		
	}

	@GetMapping("/reunion/cliente")
	public List<Reunion> listReunionesCliente(Principal principal){
		
	Usuario usuario = usuarioService.findByUsername(principal.getName());
		
		if(reunionService.findByUserId(usuario.getId()).isEmpty()) {
			return null;
		}
		return reunionService.findByUserId(usuario.getId());
		
	}

	@Secured({"ROLE_ADMIN", "ROLE_CLIENTE"})
	@PostMapping("/reunion/{id}")
	public ResponseEntity<?> agendarReunion(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaInicio,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaFin, @PathVariable Long id, Principal principal) {
		Servicio servicio = servicioService.findById(id);

		Usuario usuario = usuarioService.findByUsername(principal.getName());
		
		Map<String, Object> response = new HashMap<>();
		
		Reunion reunion = new Reunion();
	

		try {

			reunion.setCodigoReunion(null);
			reunion.setEstado("Pendiente");
			reunion.setServicio(servicio);
			reunion.setUsuario(usuario);
			reunion.setFechaInicio(fechaInicio);
			reunion.setFechaTermino(fechaFin);
			reunionService.save(reunion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reunion ha sido agendado con exito!");
		response.put("servicio", reunion);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/reunion/{id}")
	public ResponseEntity<?> agregarLinkReunion(@RequestParam String linkReunion, @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		
		Reunion reunion = reunionService.findById(id);
	

		try {

			reunion.setCodigoReunion(linkReunion);
			reunion.setEstado("Agendada");

		
			reunionService.save(reunion);
		

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reunion ha sido modificada con exito!");
		response.put("servicio", reunion);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	

}
