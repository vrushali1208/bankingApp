package com.example.BankingApp.Customer;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
    @SequenceGenerator(
            name = "cust_seq",
            sequenceName = "customer_seq",
            allocationSize = 1
    )
    private Long customerId;
	
	private String fname;
	
	private String lname;
	
	private String email;
	
	private String phoneNo;
	
	private String address;
	
	//YYYY-MM-DD
	private Date dob;
	
	private String panNumber;
	
	//Active/Inactive - if customer is blocked or closed
	private String status;
	
	private Date createdDate;
	
	public CustomerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "customerEntity [customerId=" + customerId + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", dob=" + dob + ", panNumber=" + panNumber
				+ ", status=" + status + ", createdDate=" + createdDate + "]";
	}

	public CustomerEntity(Long customerId, String fname, String lname, String email, String phoneNo, String address,
			Date dob, String panNumber, String status, Date createdDate) {
		super();
		this.customerId = customerId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.dob = dob;
		this.panNumber = panNumber;
		this.status = status;
		this.createdDate = createdDate;
	}
	
}
