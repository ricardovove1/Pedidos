package com.ma.pedidos.services;

import java.util.List;

import com.ma.pedidos.entities.OrderDetail;

public interface OrderDetailService {

	/**
	 * Save all the {@link OrderDetail}
	 * 
	 * @param orderDetails {@link List} of {@link OrderDetail}
	 * @return {@link List} of {@link OrderDetail}
	 */
	public List<OrderDetail> saveAll(List<OrderDetail> orderDetails);

}
