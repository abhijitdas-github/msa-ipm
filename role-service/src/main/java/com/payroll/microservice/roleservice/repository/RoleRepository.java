package com.payroll.microservice.roleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.microservice.roleservice.model.Payroll;

public interface RoleRepository extends JpaRepository<Payroll, Long> {

	public Payroll findByRoleName(String roleNameX);

}
