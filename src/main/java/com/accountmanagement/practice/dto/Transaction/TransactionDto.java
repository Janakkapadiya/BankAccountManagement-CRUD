package com.accountmanagement.practice.dto.Transaction;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto {
    private int amount;
    private String transactionType;
}
