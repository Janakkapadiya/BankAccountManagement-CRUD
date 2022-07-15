package com.accountmanagement.practice.Controller;
import com.accountmanagement.practice.Exceptions.NotSufficientBalance;
import com.accountmanagement.practice.dto.requests.CreateAccountReq;
import com.accountmanagement.practice.dto.requests.DepositMoneyDto;
import com.accountmanagement.practice.dto.requests.WithdrawAmountReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Model.Accounts;
import com.accountmanagement.practice.Services.BankAccountService;

import javax.validation.Valid;

@RestController
public class Controller {
 
 private BankAccountService bankAccountService;
 public Controller(BankAccountService bankAccountService) {
	super();
	this.bankAccountService = bankAccountService;
}

@GetMapping("/findById/{id}")
 public Accounts findById(@PathVariable("id") int id) throws AccountNotFoundException {
  return bankAccountService.findById(id);
}

 @PostMapping("/addAccount")
 public Accounts addAccounts(@RequestBody @Valid CreateAccountReq dto)
 {
     return bankAccountService.addAccount(dto.getName(), dto.getAmount());
 }

 @PutMapping ("/addMoney/{id}")
 public void addMoney(@PathVariable("id") int id, @RequestBody @Valid DepositMoneyDto dto) throws AccountNotFoundException
 {
      bankAccountService.addMoney(id, dto.getAmount());
 }

 @PutMapping("/withdraw/{id}")
 public void withdraw(@PathVariable("id") int id, @RequestBody @Valid WithdrawAmountReq dto) throws AccountNotFoundException, NotSufficientBalance {
     bankAccountService.withdraw(id, dto.getAmount());
 }

 @GetMapping("/checkBalance/{id}")
 public int checkBalance(@PathVariable("id") int id) throws AccountNotFoundException {
      return bankAccountService.checkBalance(id);
 }
}
