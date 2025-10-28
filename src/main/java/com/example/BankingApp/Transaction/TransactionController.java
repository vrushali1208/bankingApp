package com.example.BankingApp.Transaction;

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
import com.example.BankingApp.Account.AccountEntity;
import com.example.BankingApp.Account.AccountService;
import com.example.BankingApp.Response.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/")
	public String get() {
		return "Welcome to Transaction Controller";
	}
	
	@PostMapping("/new")
	public ResponseEntity<ApiResponse<TransactionEntity>> saveTransaction(@Valid @RequestBody TransactionEntity transactionEntity){
		
		if(transactionEntity.getAccount() == null || transactionEntity.getAccount().getAccountId() == null) {
			throw new ResourceNotFoundException("Account information is required to create Transaction!!!");
		}
		Long accountId = transactionEntity.getAccount().getAccountId();
		
		AccountEntity accountEntity = accountService.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account with Id " + accountId + " not found!!!"));
		
		transactionEntity.setAccount(accountEntity);
		TransactionEntity savedTransaction = transactionService.saveTransaction(transactionEntity);
		ApiResponse<TransactionEntity> response = new ApiResponse<>("Transaction Saved Successfully!!!", savedTransaction, true);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/all/transactions")
	public ResponseEntity<ApiResponse<List<TransactionEntity>>> getAllTransactions(){
		List<TransactionEntity> transactions = transactionService.getAllTransactions();
		ApiResponse<List<TransactionEntity>> transactionResponse;
		
		if(transactions.isEmpty()) {
			transactionResponse = new ApiResponse<>("No Transactions found!!!", null, true);
		}
		else {
			transactionResponse = new ApiResponse<>("Transactions fetched Successfully!!!", transactions, true);
		}
		return ResponseEntity.ok(transactionResponse);
	}
	
	@GetMapping("{trasactionId}")
	public ResponseEntity<ApiResponse<TransactionEntity>> getTransactionById(@PathVariable Long transactionId){
		TransactionEntity transaction = transactionService.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction with Id " + transactionId + " not found!!!"));
		
		ApiResponse<TransactionEntity> response = new ApiResponse<>("Transaction Fetched Successfully!!!", transaction, true);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<ApiResponse<List<TransactionEntity>>> getTransactionDetailsByAccountId(@PathVariable Long accountId){
		List<TransactionEntity> transactions = transactionService.getTransactionDetailsByAccountId(accountId);
		ApiResponse<List<TransactionEntity>> response;
		
		if(transactions.isEmpty()) {
			response = new ApiResponse<>("No Transaction found for Id " + accountId + " this Account!!!", null, true);
		}
		else {
			response = new ApiResponse<>("Transaction details fetched Successfully!!!", transactions, true);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update/{transactionId}")
	public ResponseEntity<ApiResponse<TransactionEntity>> updateTransactionById(@PathVariable Long transactionId, @Valid @RequestBody TransactionEntity transactionEntity){
		TransactionEntity existingTransaction = transactionService.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction with Id " + transactionId + " not found!!!"));
		existingTransaction.setTransactionType(transactionEntity.getTransactionType());
		existingTransaction.setAmount(transactionEntity.getAmount());
		existingTransaction.setTransactionStatus(transactionEntity.getTransactionStatus());
		existingTransaction.setTransactionDate(transactionEntity.getTransactionDate());
		
		if (transactionEntity.getAccount() != null && transactionEntity.getAccount().getAccountId() != null) {
			Long accountId = transactionEntity.getAccount().getAccountId();
			AccountEntity account = accountService.findById(accountId)
					.orElseThrow(() -> new ResourceNotFoundException("Account with Id " + accountId + " not found!!!"));
			existingTransaction.setAccount(account);
		}
		TransactionEntity updateTransaction = transactionService.saveTransaction(existingTransaction);
		ApiResponse<TransactionEntity> response = new ApiResponse<>("Transaction Saved Successfully!!!", updateTransaction, true);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{transactionId}")
	public ResponseEntity<ApiResponse<Void>> deleteTransactionById(@PathVariable Long transactionId){
		transactionService.findById(transactionId)
			.orElseThrow(() -> new ResourceNotFoundException("Transaction with Id " + transactionId + "not found!!!"));
		transactionService.deleteTransactionById(transactionId);
		
		ApiResponse<Void> response = new ApiResponse<>("Transaction Deleted Successfully!!!", null, true);
		return ResponseEntity.ok(response);
	}
	
}



