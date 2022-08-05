package com.accountmanagement.practice.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class FamilyAccount extends AbstractAccounts{

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "family_group_accounts", joinColumns = {@JoinColumn(name = "USER_ID")},inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID")})
    private List<User> users;

}
