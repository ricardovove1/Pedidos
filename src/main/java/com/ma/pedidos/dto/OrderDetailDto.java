package com.ma.pedidos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class OrderDetailDto {

	@NotBlank(message = "EL producto no puede estar vació")
	@JsonProperty("producto")
	private String productId;

	@NotNull(message = "La cantidad del producto no puede estar vacía")
	@Positive(message = "La cantidad del producto debe ser mayor a 0")
	@JsonProperty("cantidad")
	private Integer amount;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
