package com.payroll.microservices.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import com.payroll.microservices.employeeservice.config.EmployeeConfiguration;

/*
 * Combination of @Configuration, @ComponentScan, @EnableAutoConfiguration
 */
@SpringBootApplication 
@EnableDiscoveryClient //Register in Eureka
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableConfigurationProperties(EmployeeConfiguration.class)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	
	//How frequent we should send the logs to zipkin
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
}
