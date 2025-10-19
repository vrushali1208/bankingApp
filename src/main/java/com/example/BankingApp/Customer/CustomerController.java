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

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<?> welcome() {
		return ResponseEntity.ok(new ApiResponse<>("Welcome to Banking App", null));
	}
	
	@GetMapping("/all/customers")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerEntity> customers = customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse<>("No Customers found!!!", customers));
		}
		else {
			return ResponseEntity.ok(new ApiResponse<>("Customers fetched Successfully!!!", customers));
		} 
	}
	
	@PostMapping("/new")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerEntity customerEntity) {
		CustomerEntity savedCustomer = customerService.saveCustomer(customerEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Customer saved successfully", savedCustomer));
    }

	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
		Optional<CustomerEntity> customerById = customerService.findById(customerId);
		if(customerById.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ApiResponse<>("Customer FOund Successfully!!!", customerById.get()));
		}
		else
		{
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>("Customer with Id " + customerId + " not found!!!", null));		
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomerById(@PathVariable Long customerId, @Valid @RequestBody CustomerEntity customerEntity){
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
					.body(new ApiResponse<>("Customer with Id " + customerId + " not found!!", null));
		}
	}
		
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId){
		Optional<CustomerEntity> existingCustomer = customerService.findById(customerId);
		if(existingCustomer.isPresent()) {
			customerService.deleteCustomerById(customerId);
			return ResponseEntity.ok(new ApiResponse<>("Customer Deleted Successfully!!!!", null));
		}
		else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>("Customer with Id " + customerId + " not found!!!", null));
		}
	}
}

