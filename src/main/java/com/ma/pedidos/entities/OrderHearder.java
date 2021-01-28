package com.ma.pedidos.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ma.pedidos.entities.enums.OrderStatus;

@Entity
@Table(name = "PEDIDOS_CABECERA")
public class OrderHearder {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "DIRECCION")
	private String direction;

	private String email;

	@Column(name = "TELEFONO")
	private String phone;

	@Column(name = "HORARIO")
	private LocalTime hours;

	@Column(name = "FECHA_ALTA")
	private LocalDate date;

	@Column(name = "MONTO_TOTAL")
	private Double totalAmount;

	@Column(name = "APLICO_DESCUENTO")
	private boolean discount;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private OrderStatus status;

	@OneToMany(mappedBy = "orderHearder")
	private List<OrderDetail> details;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

}
