package com.accountmanagement.practice.Services;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Model.Accounts;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.TransactionRepository;
import com.accountmanagement.practice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BankAccountService bankAccountService;
    public User saveUser(String firstName, String lastName)
    {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    public User getbyId(int userId)throws AccountNotFoundException
    {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent())
        {
            return byId.get();
        }else{
            throw new AccountNotFoundException();
        }
    }
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    public User linkAccounts(int userId, List<Accounts> accounts) throws AccountNotFoundException {
        User user = this.getbyId(userId);
        user.setAccounts(accounts);
        return userRepository.save(user);
    }

//    public List<Accounts> oneUserAccounts(int userId,int id) throws AccountNotFoundException {
//        User user = this.getbyId(userId);
//        Accounts accounts = bankAccountService.findById(id);
//
//    }


}

