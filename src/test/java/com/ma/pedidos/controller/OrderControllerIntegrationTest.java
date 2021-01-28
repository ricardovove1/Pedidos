package com.ma.pedidos.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.mapper.OrderDetailMapper;
import com.ma.pedidos.mapper.OrderHearderMapper;
import com.ma.pedidos.services.OrderDetailService;
import com.ma.pedidos.services.OrderHearderService;
import com.ma.pedidos.services.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { OrderController.class })
class OrderControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrderHearderService orderHearderService;

	@MockBean
	private OrderHearderMapper orderHearderMapper;

	@MockBean
	private OrderDetailMapper orderDetailMapper;

	@MockBean
	private ProductService productService;

	@MockBean
	private OrderDetailService orderDetailService;

	String bodyOk = "{\n" + "  \"fecha\" : \"2021-01-27\",\n" + "  \"direccion\" : \"calle falsa\",\n"
			+ "  \"horario\" : \"09:00\" ,\n" + "  \"detalle\" : [ {\n" + "    \"producto\" : \"123\",\n"
			+ "    \"cantidad\" : 2\n" + "  } ]" + "}";

	String bodyBadRequest = "{\n" + "  \"fecha\" : \"2021-01-27\",\n" + "  \"horario\" : \"09:00\" ,\n"
			+ "  \"detalle\" : [ {\n" + "    \"producto\" : \"123\",\n" + "    \"cantidad\" : 2\n" + "  } ]" + "}";

	@Test
	void save_theReturnOk() throws Exception {

		mvc.perform(post("/pedidos").contentType(MediaType.APPLICATION_JSON).content(bodyOk))
				.andExpect(status().isCreated());
	}

	@Test
	void save_theReturnBadRequest() throws Exception {

		mvc.perform(post("/pedidos").contentType(MediaType.APPLICATION_JSON).content(bodyBadRequest))
				.andExpect(status().isBadRequest());
	}

	@Test
	void save_theReturnNotFound() throws Exception {

		when(orderDetailMapper.convertToEntityList(ArgumentMatchers.anyList()))
				.thenThrow(new ServiceException("", HttpStatus.NOT_FOUND));

		mvc.perform(post("/pedidos").contentType(MediaType.APPLICATION_JSON).content(bodyOk))
				.andExpect(status().isNotFound());
	}

	@Test
	void getByDate_theReturnOk() throws Exception {

		mvc.perform(get("/pedidos").contentType(MediaType.APPLICATION_JSON).param("fecha", "2021-01-25"))
				.andExpect(status().isOk());
	}

	@Test
	void getByDate_theReturnBadRequest() throws Exception {

		mvc.perform(get("/pedidos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
