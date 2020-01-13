package com.example.employeepayrollservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeePayroll {
	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Id
	@GeneratedValue
	@Column(name="PAYROLL_ID")
	private Long payrollId;
	
	@Column(name="EMP_ID")
	private Long empId;
	
	@Column(name="F_NAME")
	private String firstName;
	
	@Column(name="L_NAME")
	private String lastName;
	
	@Column(name="ROLE_ID")
	private Long roleId;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	@Column(name="ROLE_DESC")
	private String roleDesc;
	
	private String port;
	
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	
	
}
