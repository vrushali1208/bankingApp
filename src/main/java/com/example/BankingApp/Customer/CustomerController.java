package com.example.BankingApp.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApp.Response.ApiResponse;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String get() {
		return "Welcome to Banking App";
	}
	
	@GetMapping("/all/customers")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerEntity> customers = customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ApiResponse<>("Customers are not available!!!", customers));
		}
		else {
			return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ApiResponse<>("Customers fetched Successfully!!!", customers));
		} 
	}
	
	@PostMapping("/new")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerEntity customerEntity) {
		customerService.saveCustomer(customerEntity);
		return ResponseEntity.ok("Customer Saved Successfully!!!");
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
		Optional<CustomerEntity> customerById = customerService.getCustomerById(customerId);
		if(customerById.isPresent()) {
			return ResponseEntity.ok(customerById.get());
		}
		else
		{
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body("Customer with Id " + customerId + " not found!!!");		
		}
	}

	@PutMapping("/customer/{customerId}")
	public ResponseEntity<?> updateCustomerById(@PathVariable Long customerId, @RequestBody CustomerEntity customerEntity){
		Optional<CustomerEntity> existingCustomer = customerService.findById(customerId);
		if(existingCustomer.isPresent()) {
			CustomerEntity customer = existingCustomer.get();
			customer.setFname(customerEntity.getFname());
			customer.setLname(customerEntity.getLname());
			customer.setEmail(customerEntity.getEmail());
			customer.setPhoneNo(customerEntity.getPhoneNo());
			customer.setAddress(customerEntity.getAddress());
			customer.setDob(customerEntity.getDob());
			customer.setPanNumber(customerEntity.getPanNumber());
			customer.setStatus(customerEntity.getStatus());
			customer.setCreatedDate(customerEntity.getCreatedDate());
			customerService.saveCustomer(customer);
			return ResponseEntity.ok(new ApiResponse<>("Customer Updated Successfully!!", customer));
		}
		else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>("Customer not found with Id " +customerId, null));
		}
	}
		
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId){
		Optional<CustomerEntity> existingCustomer = customerService.findById(customerId);
		if(existingCustomer.isPresent()) {
			customerService.deleteCustomerById(customerId);
			return ResponseEntity.ok(new ApiResponse<>("Customer Deleted Successfully!!!!", null));
		}
		else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>("Customer not found with ID: " + customerId, null));
		}
	}
}

