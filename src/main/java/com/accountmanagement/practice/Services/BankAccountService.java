package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;

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
		Transaction transaction = new Transaction();
		int newBalance = amount + accounts.getBalance();
		accounts.setBalance(newBalance);
		accountrepository.save(accounts);
		transaction.setAccounts(accounts);
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposited");
		transactionRepository.save(transaction);
	}
	
	public void withdraw(int id,int amount) throws RuntimeException
	{
		Accounts accounts = this.findById(id);
		Transaction transaction = new Transaction();
	    int updatedBalance = accounts.getBalance() - amount;
	    accounts.setBalance(updatedBalance);
		accountrepository.save(accounts);
		transaction.setAccounts(accounts);
		transaction.setAmount(amount);
		transaction.setTransactionType("Withdraw");
        transactionRepository.save(transaction);
	}
	
	public int checkBalance(int id) throws AccountNotFoundException
	{
	    Accounts accounts = this.findById(id);
		return accounts.getBalance();
	}
	
	public Accounts addAccount(String type, int amount, int userId)
	{
		User user = userService.getbyId(userId);
		Accounts account = new Accounts();

		account.setName(type);
		account.setBalance(amount);
		account.setUser(user);
		Accounts savedAccount = accountrepository.save(account);


		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposited");
		transaction.setAccounts(savedAccount);
		transactionRepository.save(transaction);

		return savedAccount;
	}
	public List<Accounts> findByName(String name) {
		return accountrepository.searchByName(name);
	}
}

