package com.ma.pedidos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PEDIDOS_DETALLE")
public class OrderDetail {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToOne
	@JoinColumn(name = "PEDIDO_CABECERA_ID")
	private OrderHearder orderHearder;

	@ManyToOne
	@JoinColumn(name = "PRODUCTO_ID")
	private Product product;

	@Column(name = "CANTIDAD")
	private Integer amount;

	@Column(name = "PRECIO_UNITARIO")
	private Double unitPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrderHearder getOrderHearder() {
		return orderHearder;
	}

	public void setOrderHearder(OrderHearder orderHearder) {
		this.orderHearder = orderHearder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
