package com.ipm.microservices.salesorderservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SalesOrderController {

	private static Logger log = LoggerFactory.getLogger(SalesOrderController.class);

	@Autowired
	CustomerSOSRepository customerSosRepository;
	
	@RequestMapping(value = "/custsos", method = RequestMethod.GET)
	public List<CustomerSOS> getCustomers() {
		return customerSosRepository.findAll();
	}

	@Autowired
	SalesOrderRepository salesOrderRepository;
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<SalesOrder> getSO() {
		return salesOrderRepository.findAll();
	}

	@Autowired
	OrderLineItemRepository orderedLineItemsRepository;
	
	@RequestMapping(value = "/orderedItems", method = RequestMethod.GET)
	public List<OrderLineItem> getlineItems() {
		return orderedLineItemsRepository.findAll();
	}
	
	

	@Autowired
	RestTemplate restTemplate;

	/*
	 * Load balanced rest template resolves url from eureka
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createSalesOrder(@RequestBody Order order) {
		String response = "";
		Double totalPrice = 0.0;
		log.info("Received Sale Order... " + Utility.objToStr(order));
		try {
			Long custId = order.getCustoId();
			CustomerSOS custSos = customerSosRepository.getOne(custId);

			log.info("Customer Name: "+ custSos.getFirstName() + " " + custSos.getLastName());
			
			List<Item> orderedItemList = new ArrayList<Item>();
			
			for (String itemName : order.getItemNameList()) {
				try {
					log.info("Validating item...");

					Item item = restTemplate.getForObject("http://item-service/item/" + itemName, Item.class);

					totalPrice = totalPrice + item.getPrice();
					log.info("Stock available for Item... " + item.getDescription());
					orderedItemList.add(item);
				} catch (Exception ex) {
					log.info("Out of stock... " + itemName);
				}
			}
			if (orderedItemList.size() > 0) {
				log.info("Number of Items confirmed for order is " + orderedItemList.size());
				Long orderId = Math.round(Math.random() * 100) * 10000 + Math.round(Math.random() * 100) * 100
						+ Math.round(Math.random() * 100) + Math.round(Math.random() * 100);

				log.info("Creating sales order record");
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setCustId(custId);
				salesOrder.setId(orderId);
				salesOrder.setOrderDate(order.getOrderDate());
				salesOrder.setOrderDesc(order.getOrderDescription());
				salesOrder.setTotalPrice(totalPrice);
				salesOrderRepository.saveAndFlush(salesOrder);
				log.info("Sales Order record created..." + Utility.objToStr(salesOrder));

				log.info("Creating Line Items");
				Map<Long, OrderLineItem> lineItemMap = new HashMap<Long, OrderLineItem>();
				for (Item orderedItem : orderedItemList) {
					log.info("Adding Item to the list with Description: " + orderedItem.getDescription());
					OrderLineItem oli = new OrderLineItem();
					oli.setId(orderId * 10 + (orderedItemList.indexOf(orderedItem)));
					oli.setName(orderedItem.getItemName());
					oli.setOrderId(orderId);
					oli.setItemQuantity(1);
					if (lineItemMap.isEmpty())
						lineItemMap.put(oli.getId(), oli);
					else {
						if (lineItemMap.containsKey(oli.getId())) {
							lineItemMap.get(oli.getId())
									.setItemQuantity(lineItemMap.get(oli.getId()).getItemQuantity() + 1);

						} else {
							lineItemMap.put(oli.getId(), oli);
						}

					}
				}

				orderedLineItemsRepository.saveAll(lineItemMap.values());
				orderedLineItemsRepository.flush();
				response = "Order#" + orderId + " created successfully";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response = "Order could not be created";
		}

		return response;
	}

	@RequestMapping(value="/")
	public String healthCheck() {
		log.info("sales-order-service is up and running!");
		return "sales-order-service is up and running!";
	}
		

}
