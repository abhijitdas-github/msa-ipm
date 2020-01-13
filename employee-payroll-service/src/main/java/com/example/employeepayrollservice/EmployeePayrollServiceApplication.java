package com.example.employeepayrollservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

//@EnableFeignClients("com.example.employeepayrollservice.feign")

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.example.employeepayrollservice.feign")
public class EmployeePayrollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollServiceApplication.class, args);
	}

	//How frequesn we should send the logs to zipkin	
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
}
