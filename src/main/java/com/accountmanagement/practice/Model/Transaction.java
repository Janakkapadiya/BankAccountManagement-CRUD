package com.accountmanagement.practice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private int transactionId;
       private int amount;
       private String transactionType;

       @ManyToOne
       @JoinColumn(name = "acc_id")
       private Accounts accounts;
   
       @Override
       public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
//                ", accounts=" + accounts +
                '}';
    }
}
