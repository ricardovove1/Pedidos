package com.ma.pedidos.services;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.OrderHearder;
import com.ma.pedidos.repositories.OrderHearderRepository;
import com.ma.pedidos.services.impl.OrderHearderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class OrderHearderServiceImplTest {

	@InjectMocks
	OrderHearderServiceImpl orderHearderService;

	@Mock
	private OrderHearderRepository orderHearderRepository;

	@Mock
	private OrderDetailService orderDetailService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void save_theReturnNotDiscount() {

		OrderHearder orderHearderEntry = getOrderHearder();

		when(orderHearderRepository.save(Mockito.any(OrderHearder.class))).thenAnswer(i -> i.getArguments()[0]);

		OrderHearder orderHearder = orderHearderService.save(orderHearderEntry, List.of(getOrderDetail()));

		assertEquals(100D, orderHearder.getTotalAmount());
		assertEquals(1, orderHearder.getDetails().size());
	}

	@Test
	void save_theReturnDiscount() throws Exception {

		Field amountApplyDiscount = OrderHearderServiceImpl.class.getDeclaredField("amountApplyDiscount");
		amountApplyDiscount.setAccessible(true);
		amountApplyDiscount.set(orderHearderService, 3);

		Field percentageApplyDiscount = OrderHearderServiceImpl.class.getDeclaredField("percentageApplyDiscount");
		percentageApplyDiscount.setAccessible(true);
		percentageApplyDiscount.set(orderHearderService, 30D);

		OrderHearder orderHearderEntry = getOrderHearder();

		when(orderHearderRepository.save(Mockito.any(OrderHearder.class))).thenAnswer(i -> i.getArguments()[0]);

		OrderDetail orderDetail = getOrderDetail();

		orderDetail.setAmount(5);

		OrderHearder orderHearder = orderHearderService.save(orderHearderEntry, List.of(orderDetail));

		assertEquals(350D, orderHearder.getTotalAmount());
		assertEquals(1, orderHearder.getDetails().size());
	}

	@Test
	void getByDate_theReturnList() {

		when(orderHearderRepository.findByDateIs(Mockito.any(LocalDate.class))).thenReturn(List.of(getOrderHearder()));

		assertFalse(orderHearderService.getByDate(LocalDate.now()).isEmpty());

	}

	private OrderHearder getOrderHearder() {

		OrderHearder orderHearder = new OrderHearder();

		orderHearder.setDate(LocalDate.now());
		orderHearder.setDirection("calle falsa");

		return orderHearder;
	}

	private OrderDetail getOrderDetail() {

		OrderDetail oerderDetail = new OrderDetail();

		oerderDetail.setAmount(1);
		oerderDetail.setUnitPrice(100D);

		return oerderDetail;
	}

}
