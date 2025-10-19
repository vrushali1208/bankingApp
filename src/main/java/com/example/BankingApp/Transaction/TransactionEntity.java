package com.example.BankingApp.Transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.example.BankingApp.Account.AccountEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	
	private BigDecimal amount;
	
	//Credit,Debit,Transfer
	private String transactionType;
	
	//success, pending, failed
	private String transactionStatus;
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private AccountEntity account;

	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "TransactionEntity [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", amount=" + amount + ", transactionType=" + transactionType + ", transactionStatus="
				+ transactionStatus + ", account=" + account + "]";
	}

	public TransactionEntity(Long transactionId, Date transactionDate, BigDecimal amount, String transactionType,
			String transactionStatus, AccountEntity account) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.account = account;
	}

}
