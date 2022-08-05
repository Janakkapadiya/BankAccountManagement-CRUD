package com.accountmanagement.practice.Model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Table(name = "accounts")
public class Account extends AbstractAccounts {

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Account(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Transaction> transaction;

}
