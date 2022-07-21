package com.accountmanagement.practice.dto.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @NotNull
    private int amount;
    @NotNull
    private String transactionType;
}
