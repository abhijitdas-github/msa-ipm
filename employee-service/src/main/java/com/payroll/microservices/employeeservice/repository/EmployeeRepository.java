package com.payroll.microservices.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.microservices.employeeservice.model.Employee;

//This is the JPA template to map Entity and primary key with Database as defined in the model
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
