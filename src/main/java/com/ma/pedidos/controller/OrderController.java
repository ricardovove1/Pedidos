package com.ma.pedidos.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.dto.OrderHearderDto;
import com.ma.pedidos.entities.OrderDetail;
import com.ma.pedidos.entities.OrderHearder;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.mapper.OrderDetailMapper;
import com.ma.pedidos.mapper.OrderHearderMapper;
import com.ma.pedidos.services.OrderHearderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"order"})
@RestController
@RequestMapping("/pedidos")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderHearderService orderHearderService;

	@Autowired
	private OrderHearderMapper orderHearderMapper;

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@ApiOperation("Servicio para crear un pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OrderHearderDto.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found") })
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public OrderHearderDto save(@Valid @RequestBody OrderHearderDto orderHearderDto) throws ServiceException {

		logger.info("Creating order");

		OrderHearder orderHearder = orderHearderMapper.convertToEntity(orderHearderDto);

		List<OrderDetail> orderDetails = orderDetailMapper.convertToEntityList(orderHearderDto.getOrderDetail());

		return orderHearderMapper.convertToDto(orderHearderService.save(orderHearder, orderDetails));
	}

	@ApiOperation("Servicio para obtener los pedidos de un día")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = OrderHearderDto.class),
			@ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<OrderHearderDto> getByDate(
			@Valid @NotNull(message = "La fecha no puede ser vacía") 
			@RequestParam(name = "fecha", required = true) 
			@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE) LocalDate date) {

		logger.info("Get orders");

		List<OrderHearder> orderHearders = orderHearderService.getByDate(date);

		return orderHearderMapper.convertToDtoList(orderHearders);
	}

}
