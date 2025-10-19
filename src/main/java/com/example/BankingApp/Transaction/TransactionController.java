package com.example.BankingApp.Transaction;

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
import com.example.BankingApp.Account.AccountEntity;
import com.example.BankingApp.Account.AccountService;
import com.example.BankingApp.Response.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public ResponseEntity<?> welcome() {
		return ResponseEntity.ok(new ApiResponse<>("Welcome to Transaction Controller", null));
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> saveTransaction(@Valid @RequestBody TransactionEntity transactionEntity){
		
		if(transactionEntity.getAccount() == null || transactionEntity.getAccount().getAccountId() == null) {
			return ResponseEntity
					.badRequest()
					.body("Account information is required");
		}
		Long accountId = transactionEntity.getAccount().getAccountId();
		
		AccountEntity accountEntity = accountService.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with Id: " + accountId));
		
		transactionEntity.setAccount(accountEntity);
		TransactionEntity savedTransaction = transactionService.saveTransaction(transactionEntity);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApiResponse<>("Transaction Saved Successfully!!!", savedTransaction));
	}
	
	@GetMapping("/all/transactions")
	public ResponseEntity<?> getAllTransactions(){
		List<TransactionEntity> transactions = transactionService.getAllTransactions();
		
		if(transactions.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse<>("No Transaction fetched!!!", null));
		}
		return ResponseEntity.ok(new ApiResponse<>("Transactions fetched Successfully!!!", transactions));
	}

	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<?> getTransactionDetailsByAccountId(@PathVariable Long accountId){
		List<TransactionEntity> transactions = transactionService.getTransactionDetailsByAccountId(accountId);
		
		if(transactions.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse<>("No Transaction details fetched!!!!", null));
		}
		return ResponseEntity.ok(new ApiResponse<>("Transaction details fetched Successfully!!!!", transactions));
	}
}



