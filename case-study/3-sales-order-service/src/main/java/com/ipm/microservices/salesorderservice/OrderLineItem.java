package com.ipm.microservices.salesorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ORDER_LINE_ITEM")
public class OrderLineItem {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="item_name")
	private String name;
	
	@Column(name="order_id")
	private Long orderId;
	
	@Column(name="item_quantity")
	private Integer itemQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	
}
