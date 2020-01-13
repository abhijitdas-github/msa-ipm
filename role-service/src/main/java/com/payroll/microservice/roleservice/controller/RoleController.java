package com.payroll.microservice.roleservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.microservice.roleservice.model.Payroll;
import com.payroll.microservice.roleservice.repository.RoleRepository;


@RestController
public class RoleController {
	
	@Autowired
	RoleRepository roleRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@RequestMapping(value="/")
	public String home() {
		return "role-service is up and running on this port!";
	}	

	@RequestMapping(value="/role/{roleNameX}", method=RequestMethod.GET)
	public Payroll getRoleByRoleName(@PathVariable String roleNameX){
		logger.info("Inside getRoleByRoleName method");
		return roleRepo.findByRoleName(roleNameX);
		
	}	
	

}
