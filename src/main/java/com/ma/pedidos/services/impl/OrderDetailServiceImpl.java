package com.ma.pedidos.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.repositories.OrderDetailRepository;
import com.ma.pedidos.services.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> saveAll(List<OrderDetail> orderDetails) {
		
		logger.info("save all OrderDetails");
		
		return orderDetailRepository.saveAll(orderDetails);
	}

}
