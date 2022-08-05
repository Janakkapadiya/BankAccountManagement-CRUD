package com.accountmanagement.practice.Repository;

import com.accountmanagement.practice.Model.FamilyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyAccountRepository extends JpaRepository<FamilyAccount,Integer> {
}
