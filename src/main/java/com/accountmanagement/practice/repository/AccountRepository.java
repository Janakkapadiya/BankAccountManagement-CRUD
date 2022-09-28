package com.accountmanagement.practice.repository;

import com.accountmanagement.practice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT n FROM Account n WHERE CONCAT(n.name) LIKE %?1%")
    List<Account> searchByName(@Param("n") String name);

}
