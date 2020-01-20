package com.ipm.microservices.itemservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
	public Item findByItemName(String itemName);
}
