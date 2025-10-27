package com.example.BankingApp.Transaction;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

public interface TransactionService {

	TransactionEntity saveTransaction(@Valid TransactionEntity transactionEntity);

	List<TransactionEntity> getAllTransactions();

	List<TransactionEntity> getTransactionDetailsByAccountId(Long accountId);

	Optional<TransactionEntity> findById(Long transactionId);

	void deleteTransactionById(Long transactionId);

}
