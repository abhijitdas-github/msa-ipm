package com.ipm.microservices.customerservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class CustomerController {
	
	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	AMQPProducer amqpProducer;

	@RequestMapping(value="/")
	public String home() {
		log.info("customer-service is up and running!");
		return "customer-service is up and running!";
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public List<Customer> getCustomers(){
		log.info("Getting all customers from Database");
		List<Customer> custList = customerRepo.findAll();
		int idx = 0;
		for(Customer cust: custList) {
			
			try {
				log.info("Found customer = [" + ++idx + ": " + new ObjectMapper().writeValueAsString(cust) +"]");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return custList;
	}	
	
	@RequestMapping(value="/customer", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomers(@RequestBody Customer customer){
		log.info("Creating customer in database table");
		customerRepo.saveAndFlush(customer);
		
		
		String customerStr = "";
		try {
			customerStr = new ObjectMapper().writeValueAsString(customer);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Saved customer " + customerStr +" in database");
		
		
		log.info("Posting message in RabbitMQ");
		amqpProducer.sendMessage(customerStr);
		log.info("Successfully posted message in RabbitMQ [" + customerStr+ "]");
		
		return customer;
		
	}		
}

