package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;

import com.accountmanagement.practice.Exceptions.NotSufficientBalance;
import com.accountmanagement.practice.Model.Accounts;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.AccountRepository;
import java.util.List;
import java.util.Optional;

import com.accountmanagement.practice.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BankAccountService{

	private final AccountRepository accountrepository;
	private final UserService userService;

	private final TransactionRepository transactionRepository;


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

	public List<Accounts> findAll()
	{
		return accountrepository.findAll();
	}

	public Page<Accounts> findByPagination(int pageNumber,int pageSize)
	{
		return accountrepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	public void addMoney(int id,int amount) throws RuntimeException
	{
		Accounts accounts = this.findById(id);
		int newBalance = accounts.getBalance() + amount;
		accounts.setBalance(newBalance);
		accountrepository.save(accounts);
		Transaction transaction = new Transaction();
		transaction.setAccounts(accounts);
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposited");
		transactionRepository.save(transaction);
	}
	
	public void withdraw(int id,int amount) throws AccountNotFoundException,NotSufficientBalance
	{
		Accounts accounts = this.findById(id);
		int updatedBalance = accounts.getBalance() - amount;
		accounts.setBalance(updatedBalance);
		accountrepository.save(accounts);
		Transaction transaction = new Transaction();
		transaction.setAccounts(accounts);
		transaction.setAmount(amount);
		transaction.setTransactionType("withdraw");
		transactionRepository.save(transaction);
	}
	
	public int checkBalance(int id) throws AccountNotFoundException
	{
	    Accounts accounts = this.findById(id);
		return accounts.getBalance();
	}

	public Accounts addAccount(String name, int amount, int userId)
	{
		User user = userService.getbyId(userId);
		Accounts account = new Accounts();
		account.setName(name);
		account.setBalance(amount);
		account.setUser(user);
		Accounts accounts = accountrepository.save(account);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposited");
		transaction.setAccounts(accounts);
        transactionRepository.save(transaction);
		return  accounts;
	}
	public List<Accounts> findByName(String name) {
		return accountrepository.searchByName(name);
	}
}

