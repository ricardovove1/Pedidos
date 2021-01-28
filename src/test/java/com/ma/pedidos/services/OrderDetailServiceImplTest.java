package com.ma.pedidos.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.repositories.OrderDetailRepository;
import com.ma.pedidos.services.impl.OrderDetailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class OrderDetailServiceImplTest {

	@InjectMocks
	private OrderDetailServiceImpl orderDetailService;

	@Mock
	private OrderDetailRepository orderDetailRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveAll_theResponseListSaving() {

		List<OrderDetail> orderDetailsEntry = List.of(getOrderDetail());
		OrderDetail orderDetailExit = getOrderDetail();
		orderDetailExit.setId("a");

		when(orderDetailRepository.saveAll(orderDetailsEntry)).thenReturn(List.of(orderDetailExit));

		List<OrderDetail> orderDetail = orderDetailService.saveAll(orderDetailsEntry);

		assertEquals(1, orderDetail.size());
		assertEquals("a", orderDetail.get(0).getId());
	}

	@Test
	void saveAll_theResponseListEmpty() {

		List<OrderDetail> orderDetailsEntry = List.of(getOrderDetail());

		when(orderDetailRepository.saveAll(orderDetailsEntry)).thenReturn(Collections.emptyList());

		List<OrderDetail> orderDetail = orderDetailService.saveAll(orderDetailsEntry);

		assertTrue(orderDetail.isEmpty());

	}

	private OrderDetail getOrderDetail() {

		OrderDetail oerderDetail = new OrderDetail();

		oerderDetail.setAmount(1);
		oerderDetail.setUnitPrice(122D);

		return oerderDetail;
	}

}
