package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;

import com.accountmanagement.practice.Exceptions.NotSufficientBalance;
import com.accountmanagement.practice.Model.Accounts;
import com.accountmanagement.practice.Repository.AccountRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class BankAccountService{

	private  AccountRepository accountrepository;
	
	public BankAccountService(AccountRepository accountrepository) {
		super();
		this.accountrepository = accountrepository;
	}

	public Accounts findById(int id) throws AccountNotFoundException {
		Optional<Accounts> accountidOptional = accountrepository.findById(id);
		if(accountidOptional.isPresent())
		{
			return accountidOptional.get();
		}
		else {
			throw new AccountNotFoundException();
		}
	}
	public void addMoney(int id,int amount) throws AccountNotFoundException
	{
		Accounts accounts = this.findById(id);
//		if(accounts == null)
//		{
//			throw new AccountNotFoundException();
//		}
		int newBalance = accounts.getbalance() + amount;
		accounts.setbalance(newBalance);
		accountrepository.save(accounts);
	}
	
	public void withdraw(int id,int amount) throws AccountNotFoundException,NotSufficientBalance
	{
		Accounts accounts = this.findById(id);
//		if(accounts == null)
//		{
//			throw new AccountNotFoundException();
//		}else if (accounts.getbalance() - amount < 0) {
//			throw new NotSufficientBalance();
//		}
	    int updatedBalance = accounts.getbalance() - amount;
	    accounts.setbalance(updatedBalance);
		accountrepository.save(accounts);
	}
	
	public int checkBalance(int id) throws AccountNotFoundException
	{
	    Accounts accounts = this.findById(id);
//		if(accounts == null)
//		{
//			throw new AccountNotFoundException();
//		}
		return accounts.getbalance();
	}
	
	public Accounts addAccount(String name, int amount)
	{
		Accounts accounts = new Accounts(name,amount);
		return accountrepository.save(accounts);
	}
}

