package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Exceptions.NotSufficientBalance;
import com.accountmanagement.practice.Model.FamilyAccount;
import com.accountmanagement.practice.Services.FamilyAccountsService;
import com.accountmanagement.practice.dto.FamilyAccountReq.FamilyAccountDto;
import com.accountmanagement.practice.dto.requests.WithdrawAmountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FamliyAccountController {

    @Autowired
    private FamilyAccountsService familyAccountsService;

    @GetMapping("/findAllFamliyAccounts")
    public List<FamilyAccount> findAll() {
        return familyAccountsService.findAll();
    }


    @PostMapping("/addFamilyAccount")
    public FamilyAccount addAccount(@RequestBody FamilyAccountDto dto) {
        return familyAccountsService.addFamilyAccount(dto.getName(), dto.getAmount());
    }

    @GetMapping("/getFamilyAccountsById/{id}")
    public FamilyAccount getFamilyAccountById(@PathVariable("id") int id) throws AccountNotFoundException {
        return familyAccountsService.findFamilyMemberId(id);
    }

    @PutMapping("/addMoneyfamilyacc/{id}")
    public FamilyAccount addMoney(@RequestBody FamilyAccountDto dto, @PathVariable("id") int id) {
        return familyAccountsService.addMoney(dto.getAmount(), id);
    }


    @PutMapping("/withdrawFromFamilyAccount/{id}")
    public void withdraw(@PathVariable("id") int id, @RequestBody @Valid WithdrawAmountReq dto) throws AccountNotFoundException, NotSufficientBalance {
        familyAccountsService.withdraw(id, dto.getAmount());
    }

    @GetMapping("/checkBalanceOfFamilyAccount/{id}")
    public int checkBalance(@PathVariable("id") int id) throws AccountNotFoundException {
        return familyAccountsService.checkBalance(id);
    }

}
