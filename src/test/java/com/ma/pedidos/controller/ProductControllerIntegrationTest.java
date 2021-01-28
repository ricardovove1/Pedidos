package com.ma.pedidos.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.mapper.ProductMapper;
import com.ma.pedidos.services.OrderDetailService;
import com.ma.pedidos.services.OrderHearderService;
import com.ma.pedidos.services.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ProductController.class })
class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductMapper productMapper;

	@MockBean
	private ProductService productService;

	@MockBean
	private OrderDetailService orderDetailService;

	@MockBean
	private OrderHearderService orderHearderService;

	String bodyOk = "{\n" + "  \"descripcionCorta\": \"con tomates\",\n" + "  \"descripcionLarga\": \"con tomates\",\n"
			+ "  \"nombre\": \"napolitana\",\n" + "  \"precioUnitario\": 499\n" + "}";

	String bodyBadRequest = "{\n" + "  \"descripcionCorta\": \"con tomates\",\n"
			+ "  \"descripcionLarga\": \"con tomates\",\n" + "  \"nombre\": \"napolitana\",\n"
			+ "  \"precioUnitario\": 0\n" + "}";

	@Test
	void save_theReturnOk() throws Exception {

		mvc.perform(post("/productos").contentType(MediaType.APPLICATION_JSON).content(bodyOk))
				.andExpect(status().isCreated());
	}

	@Test
	void save_theReturnBadRequest() throws Exception {

		mvc.perform(post("/productos").contentType(MediaType.APPLICATION_JSON).content(bodyBadRequest))
				.andExpect(status().isBadRequest());
	}

	@Test
	void update_theReturnOk() throws Exception {

		mvc.perform(put("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON).content(bodyOk))
				.andExpect(status().isOk());
	}

	@Test
	void update_theReturnBadRequest() throws Exception {

		mvc.perform(put("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON).content(bodyBadRequest))
				.andExpect(status().isBadRequest());
	}

	@Test
	void update_theReturnNotFound() throws Exception {

		doThrow(new ServiceException("", HttpStatus.NOT_FOUND)).when(productService).existProduct("asd");

		mvc.perform(put("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON).content(bodyOk))
				.andExpect(status().isNotFound());
	}

	@Test
	void getAll_theReturnOk() throws Exception {

		mvc.perform(get("/productos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void getById_theReturnOk() throws Exception {

		mvc.perform(get("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void getById_theReturnNotFound() throws Exception {

		doThrow(new ServiceException("", HttpStatus.NOT_FOUND)).when(productService).getById("asd");

		mvc.perform(get("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void delete_theReturnOk() throws Exception {

		mvc.perform(delete("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void delete_theReturnNotFound() throws Exception {

		doThrow(new ServiceException("", HttpStatus.NOT_FOUND)).when(productService).getById("asd");

		mvc.perform(get("/productos/{id}", "asd").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
