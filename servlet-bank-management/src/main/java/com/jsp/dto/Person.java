package com.jsp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int person_id;
private String name;
private String c_password;
private String gender;
private String email;
private String status;

@OneToOne(cascade = CascadeType.ALL)
private Pan pan;
//
//@OneToMany
//private List<Bank> banks;
@OneToOne(cascade = CascadeType.ALL)
private BankAccount bankAccount;
//@ManyToMany
//private List<Manager> managers;

public String getName() {
	return name;
}
public int getPerson_id() {
	return person_id;
}
public void setPerson_id(int person_id) {
	this.person_id = person_id;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Pan getPan() {
	return pan;
}
public void setPan(Pan pan) {
	this.pan = pan;
}
public BankAccount getBankAccount() {
	return bankAccount;
}

public void setBankAccount(BankAccount bankAccount) {
	this.bankAccount = bankAccount;
}
public String getC_password() {
	return c_password;
}
public void setC_password(String c_password) {
	this.c_password = c_password;
}

}
