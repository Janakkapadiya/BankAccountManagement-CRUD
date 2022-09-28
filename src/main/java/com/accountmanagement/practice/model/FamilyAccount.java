package com.accountmanagement.practice.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class FamilyAccount extends AbstractAccounts {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "family_group_accounts", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID")})
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
