package com.ma.pedidos.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ma.pedidos.dto.OrderHearderDto;
import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.entities.OrderHearder;
import com.ma.pedidos.entities.Product;

@Component
public class ProductMapper {

	@Autowired
	private ModelMapper mapper;

	/**
	 * Converts dto a entity
	 * 
	 * @param orderDetailDto {@link ProductDto}
	 * @return {@link Product}
	 */
	public Product convertToEntity(ProductDto productDto) {

		if (productDto == null)
			return null;

		return mapper.map(productDto, Product.class);
	}

	/**
	 * Converts entity a dto
	 * 
	 * @param orderDetail {@link Product}
	 * @return {@link ProductDto}
	 */
	public ProductDto convertToDto(Product product) {

		if (product == null)
			return null;

		return mapper.map(product, ProductDto.class);
	}

	/**
	 * Converts list of entity a list of dto
	 * 
	 * @param orderDetails {@link List} of {@link Product}
	 * @return {@link List} of {@link ProductDto}
	 */
	public List<ProductDto> convertToDtoList(List<Product> products) {

		if (CollectionUtils.isEmpty(products))
			return Collections.emptyList();

		return products.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}
