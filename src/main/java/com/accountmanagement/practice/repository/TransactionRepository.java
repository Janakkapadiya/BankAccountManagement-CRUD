package com.accountmanagement.practice.repository;

import com.accountmanagement.practice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
