package com.dekoi.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dekoi.backend.dao.ICategoriaDao;
import com.dekoi.backend.models.Categoria;

@SpringBootTest
public class DekoiCategoriaServiceTest {

	@Autowired
	private ICategoriaService categoriaService;

	@MockBean
	private ICategoriaDao categoriaDao;

	@Test
	public void getAllCategoriasTestSucces() {
		when(categoriaDao.findAll()).thenReturn(
				Stream.of(new Categoria(1, "Sillas"), new Categoria(2, "Mesas")).collect(Collectors.toList()));
		assertEquals(2, categoriaService.findAll().size());

	}

	@Test
	public void getCategoriaTestFail() {
		when(categoriaDao.findAll()).thenReturn(
				Stream.of(new Categoria(1, "Comedor"), new Categoria(2, "Lavado")).collect(Collectors.toList()));
		assertNotEquals(200, categoriaService.findAll().size());
	}

	@Test
	public void saveCategoriaTestSucces() {
		Categoria categoria = new Categoria(1, "Dormitorio");
		when(categoriaDao.save(categoria)).thenReturn(categoria);
		assertEquals(categoria, categoriaService.save(categoria));

	}

	@Test
	public void saveCategoriaTestFail() {
		Categoria categoria = new Categoria(1, "Dormitorio");
		Categoria categoria2 = new Categoria(2, "Sillas de madera");
		when(categoriaDao.save(categoria)).thenReturn(categoria);
		assertNotEquals(categoria2, categoriaService.save(categoria));

	}

	@Test
	public void findCategoriaByIdTestSucces() {

		Categoria categoria = new Categoria(2, "Sillas de madera");

		Long id = (long) 2;
		when(categoriaDao.findById(id)).thenReturn((Optional.of(categoria)));
		assertEquals(categoria, categoriaService.findById(id));

	}

	@Test
	public void findCategoriaByIdTestFail() {

		Categoria categoria = new Categoria(2, "Sillas de madera");
		Categoria categoria2 = new Categoria(1, "Baño");
		Categoria categoria3 = new Categoria(3, "Sillas");
		Long id = (long) 2;
		when(categoriaDao.findById(id)).thenReturn((Optional.of(categoria)));
		assertNotEquals(categoria2, categoriaService.findById(id));
		assertNotEquals(categoria3, categoriaService.findById(id));

	}
	
	@Test
	public void deleteCategoriaTestSucces() {

		
		Long id = (long) 2;

		categoriaService.delete(id);
		
		verify(categoriaDao, times(1)).deleteById(id);

	}

}
