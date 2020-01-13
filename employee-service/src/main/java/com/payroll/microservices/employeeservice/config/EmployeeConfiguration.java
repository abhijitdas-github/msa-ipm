package com.payroll.microservices.employeeservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//Bean to read values from prop file - for constant and static values externalization.

@Component //Spring boot managed custom bean, can be autowired
@ConfigurationProperties(prefix="emp-svc")
public class EmployeeConfiguration {
	
	private String defaultFN;
	private String defaultLN;
	
	public String getDefaultFN() {
		return defaultFN;
	}
	public void setDefaultFN(String defaultFN) {
		this.defaultFN = defaultFN;
	}
	public String getDefaultLN() {
		return defaultLN;
	}
	public void setDefaultLN(String defaultLN) {
		this.defaultLN = defaultLN;
	}

}
