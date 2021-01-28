package com.ma.pedidos.services;

import java.time.LocalDate;
import java.util.List;

import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.OrderHearder;

public interface OrderHearderService {

	/**
	 * Save the {@link OrderHearder}
	 * 
	 * @param orderHearder {@link OrderHearder}
	 * @param orderDetails {@link List} of {@link OrderDetail}
	 * @return {@link OrderHearder}
	 */
	public OrderHearder save(OrderHearder orderHearder, List<OrderDetail> orderDetails);

	/**
	 * Get list of {@link OrderHearder} by date
	 * 
	 * @param date {@link LocalDate}
	 * @return {@link List} of {@link OrderDetail}
	 */
	public List<OrderHearder> getByDate(LocalDate date);
}
