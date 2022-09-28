package com.accountmanagement.practice.controller;

import com.accountmanagement.practice.dto.requests.CreateAccountReq;
import com.accountmanagement.practice.dto.requests.DepositMoneyDto;
import com.accountmanagement.practice.dto.requests.WithdrawAmountReq;
import com.accountmanagement.practice.exceptions.AccountNotFoundException;
import com.accountmanagement.practice.exceptions.NotSufficientBalance;
import com.accountmanagement.practice.model.Accounts;
import com.accountmanagement.practice.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final BankAccountService bankAccountService;

    @GetMapping("/findAll")
    public List<Accounts> findAll() {
        return bankAccountService.findAll();
    }

    @GetMapping("/search")
    public List<Accounts> findByName(@RequestParam("name") String name) {
        return bankAccountService.findByName(name);
    }

    @GetMapping("/page")
    public Page<Accounts> findByPagination(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber, @RequestParam
            (value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return bankAccountService.findByPagination(pageNumber, pageSize);
    }

    @GetMapping("/findById/{id}")
    public Accounts findById(@PathVariable("id") int id) throws AccountNotFoundException {
        return bankAccountService.findById(id);
    }

    @PostMapping("/addAccount/{userId}")
    public Accounts addAccounts(@RequestBody @Valid CreateAccountReq dto, @PathVariable("userId") int userId) {
        return bankAccountService.addAccount(dto.getName(), dto.getAmount(), userId);
    }

    @PutMapping("/addMoney/{id}")
    public void addMoney(@PathVariable("id") int id, @RequestBody @Valid DepositMoneyDto dto) throws AccountNotFoundException {
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
