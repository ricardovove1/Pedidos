package com.ma.pedidos.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ma.pedidos.dto.OrderDetailDto;
import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.Product;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.services.ProductService;

@Component
public class OrderDetailMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductService productService;

	/**
	 * Converts dto a entity
	 * 
	 * @param orderDetailDto {@link OrderDetailDto}
	 * @return {@link OrderDetail}
	 * @throws ServiceException
	 */
	public OrderDetail convertToEntity(OrderDetailDto orderDetailDto) throws ServiceException {

		if (orderDetailDto == null)
			return null;

		OrderDetail orderDetail = mapper.map(orderDetailDto, OrderDetail.class);

		Product product = productService.getById(orderDetailDto.getProductId());

		orderDetail.setProduct(product);
		orderDetail.setUnitPrice(product.getUnitPrice());

		return orderDetail;
	}

	/**
	 * Converts entity a dto
	 * 
	 * @param orderDetail {@link OrderDetail}
	 * @return {@link OrderDetailDto}
	 */
	public OrderDetailDto convertToDto(OrderDetail orderDetail) {

		if (orderDetail == null)
			return null;

		OrderDetailDto orderDetailDto = mapper.map(orderDetail, OrderDetailDto.class);

		Product product = orderDetail.getProduct();

		if (product != null)
			orderDetailDto.setProductId(product.getId());

		return orderDetailDto;
	}

	/**
	 * Converts list of entity a list of dto
	 * 
	 * @param orderDetails {@link List} of {@link OrderDetail}
	 * @return {@link List} of {@link OrderDetailDto}
	 */
	public List<OrderDetailDto> convertToDtoList(List<OrderDetail> orderDetails) {

		if (CollectionUtils.isEmpty(orderDetails))
			return Collections.emptyList();

		return orderDetails.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * Converts list of dto a list of entity
	 * 
	 * @param orderDetails {@link List} of {@link OrderDetailDto}
	 * @return {@link List} of {@link OrderDetail}
	 * @throws ServiceException
	 */
	public List<OrderDetail> convertToEntityList(List<OrderDetailDto> orderDetailDtos) throws ServiceException {

		if (CollectionUtils.isEmpty(orderDetailDtos))
			return Collections.emptyList();

		List<OrderDetail> orderDetails = new ArrayList<>();

		for (OrderDetailDto orderDetailDto : orderDetailDtos) {

			orderDetails.add(convertToEntity(orderDetailDto));
		}

		return orderDetails;
	}

}
