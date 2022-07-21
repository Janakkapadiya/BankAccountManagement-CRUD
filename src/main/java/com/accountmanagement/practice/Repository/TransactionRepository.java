package com.accountmanagement.practice.Repository;

import com.accountmanagement.practice.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
