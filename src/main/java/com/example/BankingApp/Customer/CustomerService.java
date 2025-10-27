package com.example.BankingApp.Customer;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

public interface CustomerService{

	List<CustomerEntity> getAllCustomers();

    Optional<CustomerEntity> findById(Long customerId);

    CustomerEntity saveCustomer(CustomerEntity customerEntity);

    void deleteCustomerById(Long customerId);

}
