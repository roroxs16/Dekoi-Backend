package com.dekoi.backend.controllers;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dekoi.backend.service.IImagenServicioService;
import com.dekoi.backend.service.IServicioService;
import com.dekoi.backend.service.IUploadService;
import com.dekoi.backend.models.ImagenServicio;
import com.dekoi.backend.models.Servicio;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ServicioController {

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private IImagenServicioService imagenServicioService;

	@Autowired
	private IUploadService uploadService;

	@GetMapping("/servicios")
	public List<Servicio> listarServicio() {
		return servicioService.findAll();
	}

	@GetMapping("/servicios/page/{page}")
	public Page<Servicio> listarServicioPaginables(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 9);
		return servicioService.findAll(pageable);
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

		response.put("mensaje", "El producto ha sido creado con exito!");
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
			response.put("mensaje", "Error: no se pudo editar, la Categoria con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			servicioActual.setNombre(servicio.getNombre());
			servicioActual.setDescripcion(servicio.getDescripcion());

			servicioActualizado = servicioService.save(servicioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto a ha sido actualizado con éxito!");
		response.put("servicio", servicioActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/servicio/{id}")
	public ResponseEntity<?> deleteServicio(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		Servicio servicio = servicioService.findById(id);

		try {

			for (ImagenServicio imagenServicio : servicio.getImagenes()) {
				uploadService.eliminar(imagenServicio.getNombre());
			}
			servicioService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "producto eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/servicio/img")
	public ResponseEntity<?> saveImagenServicio(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Servicio servicio = servicioService.findById(id);

		ImagenServicio imagen = new ImagenServicio();

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;

			try {

				nombreArchivo = uploadService.copiar(archivo);

			} catch (Exception e) {
				response.put("mensaje", "Error al subir la imagen del producto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			imagen.setNombre(nombreArchivo);
			imagen.setServicio(servicio);

			imagenServicioService.save(imagen);

			response.put("servicio", servicio);
			response.put("mensaje", "Se ha subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/servicio/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFotoServicio(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@GetMapping("/servicio/imagenes/{id}")
	public List<ImagenServicio> probandoQuery(@PathVariable Long id) {

		return imagenServicioService.findByServicioId(id);

	}

}