package com.accountmanagement.practice.Controller;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Services.TransactionService;
import com.accountmanagement.practice.dto.Transaction.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/Transaction/{id}")
    public Transaction saveTransaction(@PathVariable("id") int id,@RequestBody TransactionDto dto)
    {
        return transactionService.saveTransaction(dto.getAmount(),dto.getTransactionType(),id);
    }

    @GetMapping("/findViaId/{id}")
    public Transaction findById(@PathVariable("id") int id)
    {
        return transactionService.findById(id);
    }
}
