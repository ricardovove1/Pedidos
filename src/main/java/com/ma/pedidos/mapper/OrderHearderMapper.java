package com.ma.pedidos.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ma.pedidos.dto.OrderDetailDto;
import com.ma.pedidos.dto.OrderHearderDto;
import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.OrderHearder;
import com.ma.pedidos.exception.ServiceException;

@Component
public class OrderHearderMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	/**
	 * Converts dto a entity
	 * 
	 * @param orderDetailDto {@link OrderHearderDto}
	 * @return {@link OrderHearder}
	 */
	public OrderHearder convertToEntity(OrderHearderDto orderHearderDto) {

		return mapper.map(orderHearderDto, OrderHearder.class);
	}

	/**
	 * Converts entity a dto
	 * 
	 * @param orderDetail {@link OrderHearder}
	 * @return {@link OrderHearderDto}
	 */
	public OrderHearderDto convertToDto(OrderHearder orderHearder) {

		OrderHearderDto orderHearderDto = mapper.map(orderHearder, OrderHearderDto.class);

		List<OrderDetailDto> orderDetailDtos = orderDetailMapper.convertToDtoList(orderHearder.getDetails());

		orderHearderDto.setOrderDetail(orderDetailDtos);

		return orderHearderDto;
	}

	/**
	 * Converts list of entity a list of dto
	 * 
	 * @param orderDetails {@link List} of {@link OrderHearder}
	 * @return {@link List} of {@link OrderHearderDto}
	 */
	public List<OrderHearderDto> convertToDtoList(List<OrderHearder> orderHearders) {

		if (CollectionUtils.isEmpty(orderHearders))
			return Collections.emptyList();

		return orderHearders.stream().map(this::convertToDto).collect(Collectors.toList());
	}
}
