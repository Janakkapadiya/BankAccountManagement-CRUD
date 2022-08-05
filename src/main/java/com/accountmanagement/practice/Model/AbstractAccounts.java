package com.accountmanagement.practice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAccounts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id",nullable = false)
    private int id;

    @Column(name = "ac_type")
    private String accountType;

    @Column(name = "balance")
    private int balance;

}
