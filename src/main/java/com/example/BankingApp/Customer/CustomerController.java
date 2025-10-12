package com.example.BankingApp.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String get() {
		return "Welcome to Banking App";
	}
	
	@GetMapping("/all")
	public List<CustomerEntity> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/new")
	public CustomerEntity createCustomer(@RequestBody CustomerEntity customerEntity) {
		return customerService.saveCustomer(customerEntity);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long customerId){
		Optional<CustomerEntity> customerById = customerService.getCustomerById(customerId);
		return customerById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerEntity> updateCustomerById(@PathVariable Long customerId, @RequestBody CustomerEntity customerEntity){
		try {
			CustomerEntity updatedPatientById = customerService.updateCustomerById(customerId, customerEntity);
			return ResponseEntity.ok(updatedPatientById);
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId){
		customerService.deleteCustomerById(customerId);
		return ResponseEntity.noContent().build();
	}
}
