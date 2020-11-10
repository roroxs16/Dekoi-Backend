package com.dekoi.backend.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dekoi.backend.form.ProductoForm;
import com.dekoi.backend.models.Carrito;
import com.dekoi.backend.models.CarritoProducto;
import com.dekoi.backend.models.Compra;
import com.dekoi.backend.models.Direccion;
import com.dekoi.backend.models.Producto;
import com.dekoi.backend.models.Usuario;
import com.dekoi.backend.service.ICarritoProductoService;
import com.dekoi.backend.service.ICarritoService;
import com.dekoi.backend.service.ICompraService;
import com.dekoi.backend.service.IDireccionService;
import com.dekoi.backend.service.IProductoService;
import com.dekoi.backend.service.IUsuarioService;

@CrossOrigin(origins = { "*" })
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

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IDireccionService direccionService;

	@RequestMapping("/carrito")
	public List<CarritoProducto> listarCarritoDeCompra(Principal principal) {
		Usuario usuario = usuarioService.findByUsername(principal.getName());

		Carrito searchCarrito = new Carrito();

		for (Carrito carrito : usuario.getCarritos()) {
			if (carrito.isEstado() == true) {
				searchCarrito = carrito;
				break;
			}
		}
		return carritoProductoService.findByCarritoId(searchCarrito.getId());
	}
	
	@GetMapping("/carrito/productos")
	public List<CarritoProducto> listarCarritoDeCompraByCompra(@RequestParam long id) {
	
		return carritoProductoService.findByCarritoId(id);
	}

	@PostMapping("/carrito/crear")
	public ResponseEntity<?> crearCarrito() {

		Carrito carrito = new Carrito();

		Map<String, Object> response = new HashMap<>();

		try {
			carrito.setEstado(false);
			carrito.setValor(0);
			carritoService.save(carrito);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se creo el carrito exitosamente!");
		response.put("carrito", carrito);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/carrito/agregar/producto")
	public ResponseEntity<?> addProducto(@RequestBody ProductoForm productoForm, Principal principal) {
		List<Carrito> carritos = new ArrayList<Carrito>();

		CarritoProducto carritoProducto = new CarritoProducto();

		Usuario usuario = usuarioService.findByUsername(principal.getName());

		Carrito searchCarrito = new Carrito();
		// buscamos el carrito con el estado activo para poder comprar

		if (usuario.getCarritos().isEmpty()) {

			Carrito crearCarrito = new Carrito();

			crearCarrito.setEstado(true);
			crearCarrito.setValor(0);
			crearCarrito.setUsuario(usuario);

			carritoService.save(crearCarrito);

			carritos.add(crearCarrito);
			usuario.setCarritos(carritos);

		}

		for (Carrito carrito : usuario.getCarritos()) {
			if (carrito.isEstado() == true) {
				searchCarrito = carrito;

			}
		}

		Carrito carrito = carritoService.findById(searchCarrito.getId());

		Producto producto = productoService.findById(productoForm.getProductId());

		double sumaValor = carrito.getValor() + (productoForm.getQuantity() * producto.getValorUnitario());

		Map<String, Object> response = new HashMap<>();

		try {
			carrito.setValor(sumaValor);

			carritoProducto.setCarrito(carrito);
			carritoProducto.setProducto(producto);
			carritoProducto.setCantidad(productoForm.getQuantity());

			carritoProductoService.save(carritoProducto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se agrego el producto exitosamente!");
		response.put("carritoProducto", carritoProducto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// borro el producto del carrito por el ID del CarritoProducto
	@DeleteMapping("/carrito/{id}")
	public ResponseEntity<?> removeProducto(@PathVariable long id) {

		Map<String, Object> response = new HashMap<>();

		CarritoProducto carrProd = carritoProductoService.findById(id);

		Carrito carrito = carritoService.findById(carrProd.getCarrito().getId());

		double restaValor = carrito.getValor() - (carrProd.getCantidad() * carrProd.getProducto().getValorUnitario());

		try {

			carrito.setValor(restaValor);

			carritoProductoService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se elimino el producto del carrito exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/compra")
	public ResponseEntity<?> generateCompra(@RequestBody Direccion direccion, Principal principal) {

		Usuario usuario = usuarioService.findByUsername(principal.getName());

		Carrito searchCarrito = new Carrito();

		for (Carrito carrito : usuario.getCarritos()) {
			if (carrito.isEstado() == true) {

				searchCarrito = carrito;

				break;
			}
		}

		List<CarritoProducto> carritoProd = carritoProductoService.findByCarritoId(searchCarrito.getId());

		Carrito carrito = carritoService.findById(searchCarrito.getId());

		Map<String, Object> response = new HashMap<>();

		Compra compra = new Compra();

		double valorTotal = carrito.getValor();

		try {

			direccionService.save(direccion);

			carrito.setEstado(false);

			carritoService.save(carrito);
			compra.setCarrito(carrito);
			compra.setFechaCompra(new Date());
			compra.setValorTotal(valorTotal);
			compra.setEstado(false);
			compra.setUsuario(usuario);
			compra.setDireccion(direccion);
			compraService.save(compra);

			for (CarritoProducto cartProd : carritoProd) {
				if ((cartProd.getProducto().getStock() - cartProd.getCantidad()) <= 0) {
					cartProd.getProducto().setStock(0);
				} else {
					cartProd.getProducto().setStock(cartProd.getProducto().getStock() - cartProd.getCantidad());

				}
			}

			try {

				Carrito nuevoCarrito = new Carrito();

				nuevoCarrito.setEstado(true);
				nuevoCarrito.setUsuario(usuario);
				nuevoCarrito.setValor(0);

				carritoService.save(nuevoCarrito);

				List<Carrito> carritos = new ArrayList<Carrito>();

				carritos.add(nuevoCarrito);

				usuario.setCarritos(carritos);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se generó la compra exitosamente!");
		response.put("compra", compra);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/compra/{id}")
	public ResponseEntity<?> generateCompra(@RequestBody Compra compra, @PathVariable long id) {

		Compra compraSeleccionada = compraService.findById(id);

		Map<String, Object> response = new HashMap<>();

		try {
			compraSeleccionada.setCodigoEnvio(compra.getCodigoEnvio());
			compraSeleccionada.setEstado(compra.isEstado());

			compraSeleccionada.setUsuario(compra.getUsuario());

			compraSeleccionada.setDireccion(compra.getDireccion());

			compraSeleccionada.setFechaCompra(compra.getFechaCompra());
			compraSeleccionada.setValorTotal(compra.getValorTotal());
			compraService.save(compraSeleccionada);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se generó la compra exitosamente!");
		response.put("compra", compraSeleccionada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@RequestMapping("/compra/cliente")
	public List<Compra> listarComprasCliente(Principal principal) {
		Usuario usuario = usuarioService.findByUsername(principal.getName());

		return compraService.findAllComprasById(usuario.getId());

	}

//arreglar
	@RequestMapping("/compras/cliente")
	public Compra listarCompraCliente(Principal principal) {
		Usuario usuario = usuarioService.findByUsername(principal.getName());

		return compraService.findCompraByUserId(usuario.getId());

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/compra/admin")
	public List<Compra> listarCompras() {

		return compraService.findAll();

	}

}
