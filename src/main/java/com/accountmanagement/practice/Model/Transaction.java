package com.accountmanagement.practice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction{
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int transactionId;
       private int amount;
       private String transactionType;

       @JsonIgnore
       @ManyToOne
       @JoinColumn(name = "acc_id",nullable = false)
       private Accounts accounts;

       @Override
       public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
