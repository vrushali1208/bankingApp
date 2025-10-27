package com.example.BankingApp.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<CustomerEntity> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<CustomerEntity> findById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		return customerRepository.save(customerEntity);
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		customerRepository.deleteById(customerId);
		
	}
	
	

}
