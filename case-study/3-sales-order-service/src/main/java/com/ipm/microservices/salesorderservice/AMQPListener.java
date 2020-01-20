package com.ipm.microservices.salesorderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AMQPListener {
	
	private static Logger log = LoggerFactory.getLogger(AMQPListener.class);
	
	@Autowired
	CustomerSOSRepository custsosRepo;
	
    @RabbitListener(queues="${rabbitmq.queue-name}")
    public void listen(String msg) {
    	log.info("Received a new Customer... " + msg);
        
        Customer cust = (Customer) Utility.strToObj(msg, Customer.class);
        CustomerSOS custSOS = new CustomerSOS(cust.getId(), cust.getFirstName(), cust.getLastName(), cust.getEmail());
        custsosRepo.saveAndFlush(custSOS);
        String cistStr =  Utility.objToStr(custSOS);
        
        log.info("Saved CustomerSOS " + cistStr +" in database table CUSTOMER_SOS");
        
       
    }
}
