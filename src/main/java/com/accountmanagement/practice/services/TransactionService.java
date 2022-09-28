package com.accountmanagement.practice.services;

import com.accountmanagement.practice.model.Account;
import com.accountmanagement.practice.model.Transaction;
import com.accountmanagement.practice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    public Transaction saveTransaction(int amount, String transactionType, int id) {
        Account account = bankAccountService.findById(id);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setAccounts(account);
        return transactionRepository.save(transaction);
    }

    public Transaction findById(int transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }
}
