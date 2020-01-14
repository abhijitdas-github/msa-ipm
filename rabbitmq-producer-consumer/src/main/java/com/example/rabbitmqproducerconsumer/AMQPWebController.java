package com.example.rabbitmqproducerconsumer;

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
public class AMQPWebController {
	
	private static Logger log = LoggerFactory.getLogger(AMQPWebController.class);
	
	@Autowired
	AMQPProducer amqpProducer;
	
	@RequestMapping(value="/producer", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void postHelloMessage(@RequestBody Notification msg) {
		
		String messageAsString = "Test message";
		
		try {
			messageAsString = new ObjectMapper().writeValueAsString(msg);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("In postHelloMessage = " + messageAsString);
		amqpProducer.sendMessage(messageAsString);
		log.info("Successfully posted message in MQ");	
		
	}	

}
