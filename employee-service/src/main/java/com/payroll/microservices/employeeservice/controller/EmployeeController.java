package com.payroll.microservices.employeeservice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.payroll.microservices.employeeservice.config.EmployeeConfiguration;
import com.payroll.microservices.employeeservice.model.Employee;
import com.payroll.microservices.employeeservice.repository.EmployeeRepository;

@RestController
@EnableHystrix
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	EmployeeConfiguration employeeConfiguration;
	
	@Autowired
	Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value="/")
	public String home() {
		return "employee-service is up and running on this port!";
	}	

	@RequestMapping(value="/employee/{empId}", method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod="getEmployee_Fallback")
	public Employee getEmployee(@PathVariable Long empId){
		logger.info("Inside getEmployee method");
		Employee employee = empRepo.getOne(empId);
		logger.info("Port from database "+employee.getPort());
		employee.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("Actual Port from environment "+employee.getPort());
		return employee;
		
	}	
	
	public Employee getEmployee_Fallback(Long empId, Throwable exception){
		logger.info("I'm in getEmployee fault back method");
		logger.warn(exception.getMessage());
		System.out.printf("%s%n", exception);
		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setFirstName(employeeConfiguration.getDefaultFN());
		employee.setLastName(employeeConfiguration.getDefaultLN());
		employee.setDoj(new Date());
		return employee;
		
	}
	
}
