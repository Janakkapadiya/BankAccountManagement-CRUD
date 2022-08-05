package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Exceptions.NotSufficientBalance;
import com.accountmanagement.practice.Model.FamilyAccount;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.FamilyAccountRepository;
import com.accountmanagement.practice.Repository.TransactionRepository;
import com.accountmanagement.practice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class FamilyAccountsService {

    @Autowired
    private FamilyAccountRepository familyAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    ;

    public FamilyAccount findFamilyMemberId(int id) throws AccountNotFoundException {
        Optional<FamilyAccount> familyAccountOptional = familyAccountRepository.findById(id);
        if (familyAccountOptional.isPresent()) {
            return familyAccountOptional.get();
        } else {
            throw new AccountNotFoundException();
        }
    }

    public List<FamilyAccount> findAll() {
        return familyAccountRepository.findAll();
    }

    public FamilyAccount addFamilyAccount(String name, int amount) {
        List<User> users = userRepository.findAll();
        FamilyAccount familyAccount = new FamilyAccount();
        familyAccount.setBalance(amount);
        familyAccount.setAccountType(name);
        familyAccount.setUsers(users);
        FamilyAccount familyAccountStore = familyAccountRepository.save(familyAccount);
        return familyAccountStore;
    }

    public FamilyAccount addMoney(int id, int amount) throws RuntimeException {
        FamilyAccount familyMemberId = this.findFamilyMemberId(id);
        int newBalance = familyMemberId.getBalance() + amount;
        familyMemberId.setBalance(newBalance);
        familyAccountRepository.save(familyMemberId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposited");
        transactionRepository.save(transaction);
        return familyMemberId;
    }

    public void withdraw(int id, int amount) throws AccountNotFoundException, NotSufficientBalance {
        FamilyAccount familyMemberId = this.findFamilyMemberId(id);
        int updatedBalance = familyMemberId.getBalance() - amount;
        familyMemberId.setBalance(updatedBalance);
        familyAccountRepository.save(familyMemberId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("withdraw");
        transactionRepository.save(transaction);
    }

    public void Deposit(int id, int amount) throws AccountNotFoundException, NotSufficientBalance {
        FamilyAccount familyMemberId = this.findFamilyMemberId(id);
        int updatedBalance = familyMemberId.getBalance() + amount;
        familyMemberId.setBalance(updatedBalance);
        familyAccountRepository.save(familyMemberId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("deposited");
        transactionRepository.save(transaction);
    }

    public int checkBalance(int id) throws AccountNotFoundException {
        FamilyAccount familyMemberId = this.findFamilyMemberId(id);
        return familyMemberId.getBalance();
    }
}
