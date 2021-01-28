package com.ma.pedidos.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.OrderHearder;
import com.ma.pedidos.entities.enums.OrderStatus;
import com.ma.pedidos.repositories.OrderHearderRepository;
import com.ma.pedidos.services.OrderDetailService;
import com.ma.pedidos.services.OrderHearderService;

@Service
public class OrderHearderServiceImpl implements OrderHearderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${pedidos.descuento.cantidad-aplicar}")
	private Integer amountApplyDiscount;

	@Value("${pedidos.descuento.porcentaje-aplicar}")
	private Double percentageApplyDiscount;

	@Autowired
	private OrderHearderRepository orderHearderRepository;

	@Autowired
	private OrderDetailService orderDetailService;

	@Transactional
	@Override
	public OrderHearder save(OrderHearder orderHearder, List<OrderDetail> orderDetails) {

		logger.info("save OrderHearder");
		
		completeOrderHeaderAttributes(orderHearder, orderDetails);

		orderHearder.setStatus(OrderStatus.PENDIENTE);

		orderHearder.setDetails(orderDetails);

		orderHearder = orderHearderRepository.save(orderHearder);

		if (!CollectionUtils.isEmpty(orderDetails))
			saveOrderDetailList(orderHearder, orderDetails);

		return orderHearder;
	}

	/**
	 * Fill in missing {@link OrderHearder} attributes and calculate if you have a
	 * discount
	 * 
	 * @param orderHearder {@link OrderHearder}
	 * @param orderDetails {@link List} of {@link OrderDetail}
	 */
	private void completeOrderHeaderAttributes(OrderHearder orderHearder, List<OrderDetail> orderDetails) {

		/*
		 * if orderDetails is empty then end method
		 */
		if (CollectionUtils.isEmpty(orderDetails))
			return;

		boolean isDiscount = false;

		Integer amountProduct = 0;

		Double total = 0D;

		for (OrderDetail orderDetail : orderDetails) {

			amountProduct += orderDetail.getAmount();

			total += orderDetail.getUnitPrice() * orderDetail.getAmount();
		}

		/*
		 * evaluate if it has a discount
		 */
		if (percentageApplyDiscount != null && amountApplyDiscount != null && amountProduct > amountApplyDiscount) {

			logger.info("order with discount");
			
			isDiscount = true;

			total -= (total * percentageApplyDiscount) / 100;
		}

		orderHearder.setTotalAmount(total);
		orderHearder.setDiscount(isDiscount);

	}

	private void saveOrderDetailList(final OrderHearder orderHearder, List<OrderDetail> orderDetails) {

		orderDetails.forEach(o -> o.setOrderHearder(orderHearder));

		orderDetailService.saveAll(orderDetails);

	}

	@Override
	public List<OrderHearder> getByDate(LocalDate date) {

		return orderHearderRepository.findByDateIs(date);
	}

}
