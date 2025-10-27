package com.example.BankingApp.Account;

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
import com.example.BankingApp.Customer.CustomerEntity;
import com.example.BankingApp.Customer.CustomerService;
import com.example.BankingApp.Response.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String get() {
		return "Welcome to Account Controller";
	}
	
	@PostMapping("/new")
	public ResponseEntity<ApiResponse<AccountEntity>> saveAccount(@Valid @RequestBody AccountEntity accountEntity){

		if (accountEntity.getCustomer() == null || accountEntity.getCustomer().getCustomerId() == null) {
	        throw new ResourceNotFoundException("Customer information is required to create an Account!!!");
	    }
		
	    Long customerId = accountEntity.getCustomer().getCustomerId();

	    CustomerEntity customerEntity = customerService.findById(customerId)
	    		.orElseThrow(() -> new ResourceNotFoundException("Customer with Id " + customerId + " not found!!!"));
	    
	    accountEntity.setCustomer(customerEntity);
	    AccountEntity savedAccount = accountService.saveAccount(accountEntity);
	    ApiResponse<AccountEntity> accountResponse = new ApiResponse<>("Account Saved Successfully!!!", savedAccount, true);

	    return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
	}
	
	@GetMapping("/all/accounts")
	public ResponseEntity<ApiResponse<List<AccountEntity>>> getAllAcounts(){
		List<AccountEntity> accounts = accountService.getAllAccounts();
		
		ApiResponse<List<AccountEntity>> accountResponse;
		
		if(accounts.isEmpty()) {
			accountResponse = new ApiResponse<>("No Accounts found!!!!", null, true);
		}
		else {
			accountResponse = new ApiResponse<>("Accounts fetched Successfully!!!", accounts, true);
		}
		
		return ResponseEntity.ok(accountResponse);
	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<ApiResponse<AccountEntity>> getAccountById(@PathVariable Long accountId){
		AccountEntity account = accountService.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account with Id " + accountId + " not found!!!"));
		ApiResponse<AccountEntity> accountResponse = new ApiResponse<>("Account Fetched Successfully!!!", account, true); 
		return ResponseEntity.ok(accountResponse);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<ApiResponse<List<AccountEntity>>> getAccountDetailsByCustomer(@PathVariable Long customerId){
		List<AccountEntity> accounts = accountService.getAccountDetailsByCustomer(customerId);
		ApiResponse<List<AccountEntity>> response;

		if(accounts.isEmpty()) {
			response =  new ApiResponse<>("No Accounts found for Id " + customerId + " this Customer!!!", null, true);
		}
		else {
			response = new ApiResponse<>("Account Details fetched Successfully!!!", accounts, true);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update/{accountId}")
	public ResponseEntity<ApiResponse<AccountEntity>> updateAccountById(@PathVariable Long accountId, @Valid @RequestBody AccountEntity accountEntity){
		AccountEntity existingAccount = accountService.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account with Id " + accountId + " not found!!!"));
		existingAccount.setAccountType(accountEntity.getAccountType());
		existingAccount.setBalance(accountEntity.getBalance());
		existingAccount.setAccountStatus(accountEntity.getAccountStatus());
		existingAccount.setAccountType(accountEntity.getAccountType());
		existingAccount.setCreatedDate(accountEntity.getCreatedDate());
		
		if (accountEntity.getCustomer() != null && accountEntity.getCustomer().getCustomerId() != null) {
			Long customerId = accountEntity.getCustomer().getCustomerId();
			CustomerEntity customer = customerService.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer with Id " + customerId + " not found!!!"));
			existingAccount.setCustomer(customer);
		}
			AccountEntity updateAccount = accountService.saveAccount(existingAccount);
			ApiResponse<AccountEntity> response = new ApiResponse<>("Account Updated Successfully!!!", updateAccount, true);
			
			return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<ApiResponse<Void>> deleteAccountById(@PathVariable Long accountId){
		accountService.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account with Id " + accountId + " not found!!!"));
		accountService.deleteAccountById(accountId);
		
		ApiResponse<Void> response = new ApiResponse<>("Account Deleted Successfully!!!", null, true);
		return ResponseEntity.ok(response);
	}
	
}




