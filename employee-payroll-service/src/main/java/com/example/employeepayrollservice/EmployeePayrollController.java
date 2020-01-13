package com.example.employeepayrollservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeepayrollservice.feign.EmployeeServiceInterface;
import com.example.employeepayrollservice.feign.RoleServiceInterface;

@RestController
public class EmployeePayrollController {
	
	@Autowired
	EmployeeServiceInterface employeeServiceClient;
	
	@Autowired
	RoleServiceInterface roleServiceClient;
	
	@Autowired
	EmployeePayrollRepository repository;
	

	private static final Logger logger = LoggerFactory.getLogger(EmployeePayrollController.class);
	
	
	@PostMapping(value="/employee/{empId}/role/{roleName}")
	public EmployeePayroll insertEmployeePayrollDetails(@PathVariable Long empId, @PathVariable String roleName) {
		logger.info("Inside insertEmployeePayrollDetails method");
		
		EmployeePayroll employeePayroll = new EmployeePayroll();
		
		
		/*
		 * How we can call using RestTemplate
		 */
		
		/*
		//RestTemplate used to call other microservices
		ResponseEntity<JsonEntity> employeeEntity = new RestTemplate().getForEntity("http://localhost:8001/employee/{empId}", JsonEntity.class, empId);
		ResponseEntity<JsonEntity> roleEntity = new RestTemplate().getForEntity("http://localhost:8002/role/{roleName}", JsonEntity.class, roleName);
		
		employeePayroll.setEmpId(employeeEntity.getBody().getEmpId());
		employeePayroll.setFirstName(employeeEntity.getBody().getFirstName());
		employeePayroll.setLastName(employeeEntity.getBody().getLastName());
		employeePayroll.setRoleId(roleEntity.getBody().getRoleId());
		employeePayroll.setRoleName(roleEntity.getBody().getRoleName());
		employeePayroll.setRoleDesc(roleEntity.getBody().getDescription());
		*/
		
		/*
		 * How we can call using Feign client
		 */
		
		JsonEntity employeeEntity = employeeServiceClient.getEmployeeDetail(empId);
				
		employeePayroll.setEmpId(employeeEntity.getEmpId());
		employeePayroll.setFirstName(employeeEntity.getFirstName());
		employeePayroll.setLastName(employeeEntity.getLastName());
		
		
		
		JsonEntity roleEntity = roleServiceClient.getRoleByRoleName(roleName);
		employeePayroll.setRoleId(roleEntity.getRoleId());
		employeePayroll.setRoleName(roleEntity.getRoleName());
		employeePayroll.setRoleDesc(roleEntity.getDescription());		
		employeePayroll.setPort(Integer.toString(employeeEntity.getPort()));
		
		repository.save(employeePayroll);
		return employeePayroll;
	}
	
	@RequestMapping(value="/")
	public String home() {
		return "employee-payroll-service is up and running on this port!";
	}	

}
