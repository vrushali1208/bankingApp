package com.example.BankingApp.Account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApp.ResourceNotFoundException;
import com.example.BankingApp.Customer.CustomerEntity;
import com.example.BankingApp.Customer.CustomerService;
import com.example.BankingApp.Response.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<?> welcome() {
		return ResponseEntity.ok(new ApiResponse<>("Welcome to Account Controller", null));
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountEntity accountEntity){

		if (accountEntity.getCustomer() == null || accountEntity.getCustomer().getCustomerId() == null) {
	        return ResponseEntity
	                .badRequest()
	                .body("Customer information is required to create an account.");
	    }
		
	    Long customerId = accountEntity.getCustomer().getCustomerId();

	    CustomerEntity customerEntity = customerService.findById(customerId)
	    		.orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id: " + customerId));
	    
	    accountEntity.setCustomer(customerEntity);
	    AccountEntity savedAccount = accountService.saveAccount(accountEntity);

	    return ResponseEntity
	    		.status(HttpStatus.CREATED)
	            .body(new ApiResponse<>("Account Saved Successfully!!!", savedAccount));
	}
	
	@GetMapping("/all/accounts")
	public ResponseEntity<?> getAllAcounts(){
		List<AccountEntity> accounts = accountService.getAllAccounts();
		if(accounts.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse<>("No Accounts found!!!!", null));
		}
		
		return ResponseEntity.ok(new ApiResponse<>("Accounts fetched Successfully!!!", accounts));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getAccountDetailsByCustomer(@PathVariable Long customerId){
		List<AccountEntity> accounts = accountService.getAccountDetailsByCustomer(customerId);
		if(accounts.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse<>("No Accounts found for this Customer!!!", null));
		}
		return ResponseEntity.ok(new ApiResponse<>("Account Details fetched Successfully!!!", accounts));
	}
}




