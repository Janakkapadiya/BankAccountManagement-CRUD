package com.accountmanagement.practice.controller;

import com.accountmanagement.practice.dto.FamilyAccountReq.FamilyAccountDto;
import com.accountmanagement.practice.dto.requests.DepositMoneyDto;
import com.accountmanagement.practice.exceptions.AccountNotFoundException;
import com.accountmanagement.practice.model.FamilyAccount;
import com.accountmanagement.practice.services.FamilyAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamliyAccountController {

    @Autowired
    private FamilyAccountsService familyAccountsService;

    @GetMapping("/findAllFamliyAccounts")
    public List<FamilyAccount> findAllFamilyAccounts() {
        return familyAccountsService.findAllFamilyAccounts();
    }


    @PostMapping("/addFamilyAccount/{userId}")
    public FamilyAccount addAccount(@RequestBody FamilyAccountDto dto, @PathVariable("userId") int userId) {
        return familyAccountsService.addFamilyAccount(dto.getName(), dto.getAmount(), userId);
    }

    @PostMapping("/FamilyAccount/{id}/user/{userId}")
    public FamilyAccount addUserToFamilyAccount(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return familyAccountsService.addUserToFamilyAccount(id, userId);
    }


    @GetMapping("/getFamilyAccountsById/{id}")
    public FamilyAccount getFamilyAccountById(@PathVariable("id") int id) throws AccountNotFoundException {
        return familyAccountsService.findFamilyMemberId(id);
    }

    @PutMapping("/addMoneyFamilyAccount/{id}")
    public void addMoney(@PathVariable("id") int id, @RequestBody DepositMoneyDto dto) {
        familyAccountsService.addMoneyToFamilyAccount(id, dto.getAmount());
    }
}

