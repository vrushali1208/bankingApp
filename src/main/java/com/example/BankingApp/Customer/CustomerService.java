package com.example.BankingApp.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService{

	List<CustomerEntity> getAllCustomers();

	CustomerEntity saveCustomer(CustomerEntity customerEntity);
	
	Optional<CustomerEntity> findById(Long customerId);

	void deleteCustomerById(Long customerId);	

}
