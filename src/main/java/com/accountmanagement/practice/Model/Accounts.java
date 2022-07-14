package com.accountmanagement.practice.Model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "balance")
	private int balance;

//	public int getId() {
//		return id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getbalance() {
		return balance;
	}

	public void setbalance(int newBalance) {
		this.balance = newBalance;
	}

	public Accounts(String name, int balance) {
		super();
		this.name = name;
		this.balance = balance;
	}
	
	public Accounts() {
		super();
	}

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", name=" + name + ", amount=" + balance + "]";
	}

}
