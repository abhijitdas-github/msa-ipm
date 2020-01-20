package com.ipm.microservices.itemservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class PCFSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override  //Disable http security so that /actuator/refresh url can be called to refresh values from config-server
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}
}
