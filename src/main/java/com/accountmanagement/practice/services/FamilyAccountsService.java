package com.accountmanagement.practice.services;

import com.accountmanagement.practice.exceptions.AccountNotFoundException;
import com.accountmanagement.practice.model.Account;
import com.accountmanagement.practice.model.FamilyAccount;
import com.accountmanagement.practice.model.Transaction;
import com.accountmanagement.practice.model.User;
import com.accountmanagement.practice.repository.AccountRepository;
import com.accountmanagement.practice.repository.FamilyAccountRepository;
import com.accountmanagement.practice.repository.TransactionRepository;
import com.accountmanagement.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FamilyAccountsService {

    @Autowired
    private FamilyAccountRepository familyAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyAccountsService familyAccountsService;
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    public FamilyAccount findFamilyMemberId(int id) throws AccountNotFoundException {
        Optional<FamilyAccount> familyAccountOptional = familyAccountRepository.findById(id);
        if (familyAccountOptional.isPresent()) {
            return familyAccountOptional.get();
        } else {
            throw new AccountNotFoundException();
        }
    }

    public List<FamilyAccount> findAllFamilyAccounts() {
        return familyAccountRepository.findAll();
    }

    public FamilyAccount addFamilyAccount(String name, int amount, int userId) {
        Set<User> user = Collections.singleton(userService.getbyId(userId));
        FamilyAccount familyAccount = new FamilyAccount();
        familyAccount.setBalance(amount);
        familyAccount.setAccountType(name);
        familyAccount.setUsers(user);
        return familyAccountRepository.save(familyAccount);
    }

    public FamilyAccount addUserToFamilyAccount(int id, int userId) {
        Set<User> users = null;
        FamilyAccount familyAccount = familyAccountRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        users = familyAccount.getUsers();
        users.add(user);
        familyAccount.setUsers(users);
        return familyAccountRepository.save(familyAccount);
    }

    public void addMoneyToFamilyAccount(int id, int amount) throws AccountNotFoundException {
        FamilyAccount familyAccount = this.findFamilyMemberId(id);
        Account account = bankAccountService.findById(id);

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FamilyAccount familyAccounts = familyAccountsService.findFamilyMemberId(id);
        Set<User> users = familyAccounts.getUsers();
        Optional<User> foundUser = users.stream().filter(user -> user.getUserId() == loggedInUser.getUserId()).findFirst();

        if (foundUser.isPresent()) {
            int newBalance = familyAccount.getBalance() + amount;
            account.setBalance(newBalance);
            accountRepository.save(account);
            Transaction transaction = new Transaction();
            transaction.setAccounts(account);
            transaction.setAmount(amount);
            transaction.setTransactionType("Deposited");
            transactionRepository.save(transaction);
        } else {
            throw new IllegalArgumentException("make sure if its your account");
        }
    }
}
