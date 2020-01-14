package com.example.rabbitmqproducerconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AMQPListener {

//    @Autowired
//    private RabbitMQProperties rabbitMQProperties;	
//
//    @Bean
//    public Jackson2JsonMessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(AMQPListener listener) {
//        return new MessageListenerAdapter(listener, "listen");
//    }
    
    //This is the method which is actually listening the queue rabbitMQProperties.getQueueName()
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(rabbitMQProperties.getQueueName());
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }    
//    
//    //@RabbitListener(queues="${rabbitmq.queueName}")
//    public void listen(byte[] message) {
//        String msg = new String(message);
//        //Notification not = new Json().fromJson(msg, Notification.class);
//        System.out.println("Received a new notification...");
//        System.out.println(msg);
//    }
    
    
    @RabbitListener(queues="${rabbitmq.queueName}")
    public void listen(String msg) {
        System.out.println("Received a new notification...");
        System.out.println(msg);
        try {
			Notification not = new ObjectMapper().readValue(msg, Notification.class);
			System.out.println("Message = " + not.getMsg());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}