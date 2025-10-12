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
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		return customerRepository.save(customerEntity);
	}

	@Override
	public Optional<CustomerEntity> getCustomerById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public CustomerEntity updateCustomerById(Long customerId, CustomerEntity customerEntity) {
		CustomerEntity customer = customerRepository.findById(customerId).orElseThrow();
		customer.setFname(customerEntity.getFname());
		customer.setLname(customerEntity.getLname());
		customer.setEmail(customerEntity.getEmail());
		customer.setPhoneNo(customerEntity.getPhoneNo());
		customer.setAddress(customerEntity.getAddress());
		customer.setDob(customerEntity.getDob());
		customer.setPanNumber(customerEntity.getPanNumber());
		customer.setStatus(customerEntity.getStatus());
		customer.setCreatedDate(customerEntity.getCreatedDate());
		CustomerEntity updateCustomerById = customerRepository.save(customer);
		return updateCustomerById;
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		customerRepository.deleteById(customerId);
	}
}
