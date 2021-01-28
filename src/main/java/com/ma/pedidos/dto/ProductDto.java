package com.ma.pedidos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class ProductDto {

	@ApiModelProperty(hidden = true)
	private String id;

	@NotBlank(message = "El nombre no puede estar vació")
	@JsonProperty("nombre")
	private String name;

	@JsonProperty("descripcionCorta")
	private String shortDescription;

	@JsonProperty("descripcionLarga")
	private String longDescription;

	@Positive(message = "El precio debe ser mayor a 0")
	@JsonProperty("precioUnitario")
	private Double unitPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
