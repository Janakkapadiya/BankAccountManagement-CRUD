package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Model.FamilyAccount;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Services.FamilyAccountsService;
import com.accountmanagement.practice.dto.FamilyAccountReq.FamilyAccountDto;
import com.accountmanagement.practice.dto.requests.DepositMoneyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class FamliyAccountController {

    @Autowired
    private FamilyAccountsService familyAccountsService;
//    @Autowired
//    private FamilyAccountRepository familyAccountRepository;
//
//    @Autowired
//    private UserRepository userRepository;

    @GetMapping("/findAllFamliyAccounts")
    public List<FamilyAccount> findAllFamilyAccounts() {
        return familyAccountsService.findAllFamilyAccounts();
    }


    @PostMapping("/addFamilyAccount/{userId}")
    public FamilyAccount addAccount(@RequestBody FamilyAccountDto dto,@PathVariable("userId") int userId) {
        return familyAccountsService.addFamilyAccount(dto.getName(), dto.getAmount(),userId);
    }

    @PostMapping("/FamilyAccount/{id}/user/{userId}")
    public FamilyAccount addUserToFamilyAccount(@PathVariable("id") int id,@PathVariable("userId") int userId) {
        return familyAccountsService.addUserToFamilyAccount(id,userId);
    }


    @GetMapping("/getFamilyAccountsById/{id}")
    public FamilyAccount getFamilyAccountById(@PathVariable("id") int id) throws AccountNotFoundException {
        return familyAccountsService.findFamilyMemberId(id);
    }

    @PutMapping("/addMoneyFamilyAccount/{id}")
    public void addMoney(@PathVariable("id") int id, @RequestBody DepositMoneyDto dto)
    {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FamilyAccount familyAccounts = familyAccountsService.findFamilyMemberId(id);
        Set<User> users =  familyAccounts.getUsers();
        Optional<User> foundUser = users.stream().filter(user -> user.getUserId() == loggedInUser.getUserId()).findFirst();
        if(foundUser.isPresent()) {
            familyAccountsService.addMoneyToFamilyAccount(id, dto.getAmount());
        }else{
            throw new IllegalArgumentException("make sure if its your account");
        }
    }
//    @PutMapping("/WithdrawMoneyFromFamilyAccount/{id}")
//    public void withdraw(@PathVariable("id") int id, @RequestBody DepositMoneyDto dto)
//    {
//        familyAccountsService.withdraw(id, dto.getAmount());
//    }
}

