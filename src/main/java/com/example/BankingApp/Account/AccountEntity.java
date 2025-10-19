package com.example.BankingApp.Account;


import java.util.Date;

import com.example.BankingApp.Customer.CustomerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
	private String accountNumber;
	
	//saving, current, salary
	private String accountType;
	
	private Double balance;
	
	private Date createdDate;
	
	//active, closed, suspended
	private String accountStatus;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private CustomerEntity customer;
	
	@PrePersist
	public void prePersist() {
		if (this.accountNumber == null) {
			this.accountNumber = AccountNumberGenerator.generateAccountNumber(accountId);
		}
	}

	public AccountEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "AccountEntity [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountType="
				+ accountType + ", balance=" + balance + ", createdDate=" + createdDate + ", accountStatus="
				+ accountStatus + ", customer=" + customer + "]";
	}

	public AccountEntity(Long accountId, String accountNumber, String accountType, Double balance, Date createdDate,
			String accountStatus, CustomerEntity customer) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.createdDate = createdDate;
		this.accountStatus = accountStatus;
		this.customer = customer;
	}
	
}
