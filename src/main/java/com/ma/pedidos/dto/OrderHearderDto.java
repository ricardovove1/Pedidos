package com.ma.pedidos.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ma.pedidos.entities.enums.OrderStatus;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class OrderHearderDto {

	@JsonProperty("fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotBlank(message = "La dirección no puede estar vaciá")
	@JsonProperty("direccion")
	private String direction;

	@Email(message = "El email debe ser valido")
	private String email;

	@JsonProperty("telefono")
	private String phone;

	@NotNull(message = "El horario no puede estar vació")
	@JsonProperty("horario")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime hours;

	@Valid
	@NotEmpty(message = "El detalle no puede estar vació")
	@JsonProperty("detalle")
	private List<OrderDetailDto> orderDetail;

	@JsonProperty("total")
	@ApiModelProperty(hidden = true)
	private Double totalAmount;

	@JsonProperty("descuento")
	@ApiModelProperty(hidden = true)
	private boolean discount;

	@JsonProperty("estado")
	@ApiModelProperty(hidden = true)
	private OrderStatus status;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalTime getHours() {
		return hours;
	}

	public void setHours(LocalTime hours) {
		this.hours = hours;
	}

	public List<OrderDetailDto> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailDto> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
