package com.accountmanagement.practice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.accountmanagement.practice.Model.Accounts;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer>{

    @Query("SELECT n FROM Accounts n WHERE CONCAT(n.name) LIKE %?1%")
    List<Accounts> searchByName(@Param("n") String name);

}
