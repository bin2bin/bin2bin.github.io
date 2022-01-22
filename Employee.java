package com.hw3.model.vo.Employee;

public class Employee {
private String empName;
private String dept;
private String job;
private int age;
private char gender;
private int salary;
private double bonusPoint;
private String phone;
private String address;

public Employee() {
	// TODO Auto-generated constructor stub
}

public Employee(String empName, String dept, String job, int age, char gender, int salary, double bonusPoint,
		String phone, String address) {
	super();
	this.empName = empName;
	this.dept = dept;
	this.job = job;
	this.age = age;
	this.gender = gender;
	this.salary = salary;
	this.bonusPoint = bonusPoint;
	this.phone = phone;
	this.address = address;
}

public String getEmpName() {
	return empName;
}

public void setEmpName(String empName) {
	this.empName = empName;
}

public String getDept() {
	return dept;
}

public void setDept(String dept) {
	this.dept = dept;
}

public String getJob() {
	return job;
}

public void setJob(String job) {
	this.job = job;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public char getGender() {
	return gender;
}

public void setGender(char gender) {
	this.gender = gender;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}

public double getBonusPoint() {
	return bonusPoint;
}

public void setBonusPoint(double bonusPoint) {
	this.bonusPoint = bonusPoint;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String information() {
	return  "이름 : " + empName + " 부서 : " + dept + " 직급 : " + job + " 나이 : " + age + " 성별 : " + gender + " 급여 : " + 
	salary + " 보너스포인트 : " + bonusPoint + " 전화번호 : " + phone + " 주소 : " + address;
	
	
}

}

