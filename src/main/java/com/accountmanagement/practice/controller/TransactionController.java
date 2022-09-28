package com.accountmanagement.practice.controller;

import com.accountmanagement.practice.dto.Transaction.TransactionDto;
import com.accountmanagement.practice.model.Transaction;
import com.accountmanagement.practice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/saveTransaction/{id}")
    public Transaction saveTransaction(@PathVariable("id") int id, @RequestBody TransactionDto dto) {
        return transactionService.saveTransaction(dto.getAmount(), dto.getTransactionType(), id);
    }

    @GetMapping("/findViaId/{id}")
    public Transaction findById(@PathVariable("id") int id) {
        return transactionService.findById(id);
    }
}
