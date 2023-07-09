package com.jsp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue
	private int account_id;
	private long account_number;
	private double balance;
	private String type;
	
//	@ManyToMany
//	private List<Person> persons;
@OneToOne(cascade = CascadeType.ALL)
private Person person;
//	@ManyToOne
//	private List<Bank> banks;
	
//	@ManyToMany
//	private List<Manager> managers;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bank bank;

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
