package com.ipm.microservices.salesorderservice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="SALES_ORDER")
public class SalesOrder {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="order_date")
	private Date orderDate;	
	
	@Column(name="cust_id")
	private Long custId;
	
	@Column(name="order_desc")
	private String orderDesc;	
	
	@Column(name="total_price")
	private Double totalPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}	
	
}
