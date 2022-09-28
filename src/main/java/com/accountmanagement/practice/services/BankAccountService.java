package com.accountmanagement.practice.services;

import com.accountmanagement.practice.exceptions.AccountNotFoundException;
import com.accountmanagement.practice.exceptions.NotSufficientBalance;
import com.accountmanagement.practice.model.Account;
import com.accountmanagement.practice.model.Transaction;
import com.accountmanagement.practice.model.User;
import com.accountmanagement.practice.repository.AccountRepository;
import com.accountmanagement.practice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final AccountRepository accountrepository;
    private final UserService userService;
    private final TransactionRepository transactionRepository;

    public Account findById(int id) throws AccountNotFoundException {
        Optional<Account> accountidOptional = accountrepository.findById(id);
        if (accountidOptional.isPresent()) {
            return accountidOptional.get();
        } else {
            throw new AccountNotFoundException();
        }
    }

    public List<Account> findAll() {
        return accountrepository.findAll();
    }

    public Page<Account> findByPagination(int pageNumber, int pageSize) {
        return accountrepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public void addMoney(int id, int amount) throws RuntimeException {
        Account account = this.findById(id);
        int newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountrepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccounts(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposited");
        transactionRepository.save(transaction);
    }

    public void withdraw(int id, int amount) throws AccountNotFoundException, NotSufficientBalance {
        Account account = this.findById(id);
        int updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);
        accountrepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccounts(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("withdraw");
        transactionRepository.save(transaction);
    }

    public int checkBalance(int id) throws AccountNotFoundException {
        Account account = this.findById(id);
        return account.getBalance();
    }

    public Account addAccount(String name, int amount, int userId) {
        User user = userService.getbyId(userId);
        Account account = new Account();
        account.setAccountType(name);
        account.setBalance(amount);
        account.setUser(user);
        Account accounts = accountrepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposited");
        transaction.setAccounts(accounts);
        transactionRepository.save(transaction);
        return accounts;
    }

    public List<Account> findByName(String name) {
        return accountrepository.searchByName(name);
    }
}

