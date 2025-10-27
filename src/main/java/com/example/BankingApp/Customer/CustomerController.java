package com.example.BankingApp.Customer;

import java.util.List;

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

import com.example.BankingApp.ResourceNotFoundException;
import com.example.BankingApp.Response.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String get() {
		return "Welcome to Customer Banking App";
	}
	
	@GetMapping("/all/customers")
	public ResponseEntity<ApiResponse<List<CustomerEntity>>> getAllCustomers() {
		List<CustomerEntity> customers = customerService.getAllCustomers();
		ApiResponse<List<CustomerEntity>> response = new ApiResponse<>("All Customers fetched Successfully!!!", customers, true);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<ApiResponse<CustomerEntity>> getCustomerById(@PathVariable Long customerId){
		CustomerEntity customerById = customerService.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with Id " + customerId + " not found!!!"));
		ApiResponse<CustomerEntity> customerResponse = new ApiResponse<>("Customer Found Successfully!!!", customerById, true);
			return ResponseEntity.ok(customerResponse);		
	}
		
	@PostMapping("/new")
    public ResponseEntity<ApiResponse<CustomerEntity>> saveCustomer(@Valid @RequestBody CustomerEntity customerEntity) {
		CustomerEntity savedCustomer = customerService.saveCustomer(customerEntity);
		ApiResponse<CustomerEntity> customerResponse = new ApiResponse<>("Customer Saved Successfully!!!", savedCustomer, true);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }	

	@PutMapping("/{customerId}")
	public ResponseEntity<ApiResponse<CustomerEntity>> updateCustomerById(@PathVariable Long customerId, @Valid @RequestBody CustomerEntity customerEntity){
		CustomerEntity existingCustomer = customerService.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found!"));
		existingCustomer.setFname(customerEntity.getFname());
		existingCustomer.setLname(customerEntity.getLname());
		existingCustomer.setEmail(customerEntity.getEmail());
		existingCustomer.setPhoneNo(customerEntity.getPhoneNo());
		existingCustomer.setAddress(customerEntity.getAddress());
		existingCustomer.setDob(customerEntity.getDob());
		existingCustomer.setPanNumber(customerEntity.getPanNumber());
		existingCustomer.setStatus(customerEntity.getStatus());
		existingCustomer.setCreatedDate(customerEntity.getCreatedDate());
		
		CustomerEntity updatedCustomer = customerService.saveCustomer(existingCustomer);
		ApiResponse<CustomerEntity> customerResponse = new ApiResponse<>("Customer Updated Successfully!!", updatedCustomer, true);
		return ResponseEntity.ok(customerResponse);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse<Object>> deleteCustomerById(@PathVariable Long customerId){
		customerService.findById(customerId)
		.orElseThrow(() -> new ResourceNotFoundException("Customer with Id " + customerId + " not found!!!"));
		
		customerService.deleteCustomerById(customerId);
		ApiResponse<Object> customerResponse = new ApiResponse<>("Customer Deleted Successfully!!!!", null, true);
			return ResponseEntity.ok(customerResponse);
	}
}

