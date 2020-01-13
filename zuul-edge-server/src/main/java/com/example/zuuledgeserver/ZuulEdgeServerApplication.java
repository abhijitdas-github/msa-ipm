package com.example.zuuledgeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

/*
 * Combination of @Configuration, @ComponentScan, @EnableAutoConfiguration
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulEdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeServerApplication.class, args);
	}

	//How frequesn we should send the logs to zipkin	
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
}
