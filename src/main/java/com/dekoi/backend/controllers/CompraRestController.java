package com.dekoi.backend.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.form.ProductoForm;
import com.dekoi.backend.models.Carrito;
import com.dekoi.backend.models.CarritoProducto;
import com.dekoi.backend.models.Compra;
import com.dekoi.backend.models.Producto;
import com.dekoi.backend.service.ICarritoProductoService;
import com.dekoi.backend.service.ICarritoService;
import com.dekoi.backend.service.ICompraService;
import com.dekoi.backend.service.IProductoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CompraRestController {

	@Autowired
	private ICarritoService carritoService;

	@Autowired
	private ICarritoProductoService carritoProductoService;

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ICompraService compraService;

	@RequestMapping("/carrito/{id}")
	public List<CarritoProducto> listarCarritoDeCompra(@PathVariable Long id) {
		System.out.println(id);
		return carritoProductoService.findByCarritoId(id);
	}

	@PostMapping("/carrito/crear")
	public ResponseEntity<?> crearCarrito() {

		Carrito carrito = new Carrito();

		Map<String, Object> response = new HashMap<>();

		try {
			carrito.setEstado(false);
			carrito.setValor(0);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		carritoService.save(carrito);
		response.put("mensaje", "Se creo el carrito exitosamente!");
		response.put("carrito", carrito);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/carrito/agregar/producto/{id}")
	public ResponseEntity<?> addProducto(@RequestBody ProductoForm productoForm, @PathVariable long id) {

		CarritoProducto carritoProducto = new CarritoProducto();

		Carrito carrito = carritoService.findById(id);
		
		

		Producto producto = productoService.findById(productoForm.getProductId());
		double sumaValor=carrito.getValor()+(productoForm.getQuantity() * producto.getValorUnitario());
		

		Map<String, Object> response = new HashMap<>();

		try {
			carrito.setValor(sumaValor);
			carritoProducto.setCarrito(carritoService.findById(id));
			carritoProducto.setProducto(producto);
			carritoProducto.setCantidad(productoForm.getQuantity());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		carritoProductoService.save(carritoProducto);
		response.put("mensaje", "Se agrego el producto exitosamente!");
		response.put("carritoProducto", carritoProducto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/carrito/remove/producto/{id}")
	public ResponseEntity<?> removeProducto(@PathVariable long id) {

		Map<String, Object> response = new HashMap<>();
		
		CarritoProducto carrProd = carritoProductoService.findById(id);
		
		
		Carrito carrito = carritoService.findById(carrProd.getCarrito().getId());
		
		double restaValor=carrito.getValor() - (carrProd.getCantidad()*carrProd.getProducto().getValorUnitario());

		try {
			carrito.setValor(restaValor);
			
			carritoProductoService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se elimino el producto exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/compra/{id}")
	public ResponseEntity<?> generateCompra(@PathVariable long id){
		
		Carrito carrito = carritoService.findById(id); 
		
		Map<String, Object> response = new HashMap<>();
		
		carrito.setEstado(true);
		carritoService.save(carrito);
		Compra compra = new Compra();
		double valorTotal = carrito.getValor();
		try {
			compra.setFechaCompra(new Date());
			compra.setValorTotal(valorTotal);
			compra.setEstado(false);
			compra.setCarrito(carrito);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		compraService.save(compra);
		
		
		response.put("mensaje", "Se generó la compra exitosamente!");
		response.put("compra", compra);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
