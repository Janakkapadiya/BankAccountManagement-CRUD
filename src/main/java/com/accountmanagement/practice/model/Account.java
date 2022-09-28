package com.accountmanagement.practice.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "accounts")
public class Account extends AbstractAccounts {

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts", fetch = FetchType.LAZY)
    private List<Transaction> transaction;

    public Account(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

}
