package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Model.Account;
import com.accountmanagement.practice.Model.FamilyAccount;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.AccountRepository;
import com.accountmanagement.practice.Repository.FamilyAccountRepository;
import com.accountmanagement.practice.Repository.TransactionRepository;
import com.accountmanagement.practice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FamilyAccountsService{

    @Autowired
    private FamilyAccountRepository familyAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

//    public FamilyAccountsService(AccountRepository accountrepository, UserService userService, TransactionRepository transactionRepository) {
//        super(accountrepository, userService, transactionRepository);
//    }

    public FamilyAccount findFamilyMemberId(int id) throws AccountNotFoundException {
        Optional<FamilyAccount> familyAccountOptional = familyAccountRepository.findById(id);
        if (familyAccountOptional.isPresent()) {
            return familyAccountOptional.get();
        } else {
            throw new AccountNotFoundException();
        }
    }

    public List<FamilyAccount> findAllFamilyAccounts()
    {
        return familyAccountRepository.findAll();
    }

    public FamilyAccount addFamilyAccount(String name, int amount,int userId) {
        Set<User> user = Collections.singleton(userService.getbyId(userId));
        FamilyAccount familyAccount = new FamilyAccount();
        familyAccount.setBalance(amount);
        familyAccount.setAccountType(name);
        familyAccount.setUsers(user);
        return familyAccountRepository.save(familyAccount);
    }

    //manytomany
    public FamilyAccount addUserToFamilyAccount(int id,int userId) {
        Set<User> users = null;
        FamilyAccount familyAccount = familyAccountRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        users = familyAccount.getUsers();
            users.add(user);
            familyAccount.setUsers(users);
            return familyAccountRepository.save(familyAccount);
    }

    public void addMoneyToFamilyAccount(int id, int amount) throws RuntimeException {
        FamilyAccount familyAccount = this.findFamilyMemberId(id);
        Account account = bankAccountService.findById(id);
        int newBalance = familyAccount.getBalance() + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposited");
        transactionRepository.save(transaction);
    }

//    @Override
//    @Id
//    public void withdraw(int id, int amount) throws RuntimeException {
//    super.withdraw(id, amount);
//}
}
