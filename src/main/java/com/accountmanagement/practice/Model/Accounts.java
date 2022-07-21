package com.accountmanagement.practice.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Accounts {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int id;
	@Column(name = "ac_type")
	private String name;
	@Column(name = "balance")
	private int balance;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	public Accounts(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts",fetch = FetchType.LAZY)
	private List<Transaction> transaction;

	@Override
	public String toString() {
		return "Accounts{" +
				"name='" + name + '\'' +
				", balance=" + balance +
				", transaction=" + transaction +
				'}';
	}

}
