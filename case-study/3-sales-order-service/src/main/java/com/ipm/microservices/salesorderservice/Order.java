package com.ipm.microservices.salesorderservice;

import java.util.Date;
import java.util.List;

public class Order {
	private String orderDescription;
	private Date orderDate;
	private Long custoId;
	private List<String> itemNameList;
	
	
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Long getCustoId() {
		return custoId;
	}
	public void setCustoId(Long custoId) {
		this.custoId = custoId;
	}
	public List<String> getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(List<String> itemNameList) {
		this.itemNameList = itemNameList;
	}
	
	
}
