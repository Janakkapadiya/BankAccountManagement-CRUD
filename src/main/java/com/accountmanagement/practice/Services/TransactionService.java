package com.accountmanagement.practice.Services;
import com.accountmanagement.practice.Model.Accounts;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    public Transaction saveTransaction(int amount, String transactionType,int id){
        Accounts accounts = bankAccountService.findById(id);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setAccounts(accounts);
        return transactionRepository.save(transaction);
    }
    public Transaction findById(int transactionId)
    {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if(transaction.isPresent())
        {
            return transaction.get();
        }else{
            throw new RuntimeException("Transaction not found");
        }
    }
}
