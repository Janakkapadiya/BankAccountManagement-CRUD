package com.accountmanagement.practice.repository;

import com.accountmanagement.practice.model.FamilyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyAccountRepository extends JpaRepository<FamilyAccount, Integer> {
}
