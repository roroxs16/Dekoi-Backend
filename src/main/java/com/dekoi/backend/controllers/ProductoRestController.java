package com.dekoi.backend.controllers;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;

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

import com.dekoi.backend.service.IImagenService;
import com.dekoi.backend.service.IProductoService;
import com.dekoi.backend.service.IUploadService;
import com.dekoi.backend.models.Imagen;
import com.dekoi.backend.models.Producto;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IImagenService imagenService;

	@Autowired
	private IUploadService uploadService;

	@GetMapping("/producto")
	public List<Producto> listarProductos() {
		return productoService.findAll();
	}

	@GetMapping("/producto/page/{page}")
	public Page<Producto> listarProductosPaginables(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 9);
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

	@Secured("ROLE_ADMIN")
	@PostMapping("/producto")
	public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {

		Producto productoNuevo = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			productoNuevo = productoService.save(producto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido creado con exito!");
		response.put("producto", productoNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto, BindingResult result,
			@PathVariable Long id) {

		Producto productoActual = productoService.findById(id);

		Producto productoActualizado = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (productoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Categoria con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			productoActual.setNombre(producto.getNombre());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setStock(producto.getStock());
			productoActual.setValorUnitario(producto.getValorUnitario());
			productoActual.setCategoria(producto.getCategoria());

			productoActualizado = productoService.save(productoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto a ha sido actualizado con éxito!");
		response.put("producto", productoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		Producto producto = productoService.findById(id);

		try {

			for (Imagen imagen : producto.getImagenes()) {
				uploadService.eliminar(imagen.getNombre());
			}
			productoService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "producto eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/producto/img")
	public ResponseEntity<?> saveImagen(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Producto producto = productoService.findById(id);

		Imagen imagen = new Imagen();

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
			imagen.setProducto(producto);

			imagenService.save(imagen);

			response.put("producto", producto);
			response.put("mensaje", "Se ha subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/producto/delete/img/{id}")
	public ResponseEntity<?> eliminarFoto(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		Imagen imagen = imagenService.findById(id);

		try {

			uploadService.eliminar(imagen.getNombre());
			imagenService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Imagen de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "producto eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

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

	@GetMapping("/producto/imagenes/{id}")
	public List<Imagen> probandoQuery(@PathVariable Long id) {

		return imagenService.findByProductId(id);

	}

}
