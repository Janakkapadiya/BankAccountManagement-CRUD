package com.accountmanagement.practice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountmanagement.practice.Model.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer>{ }
