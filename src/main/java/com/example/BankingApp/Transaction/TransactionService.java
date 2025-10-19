package com.example.BankingApp.Transaction;

import java.util.List;

import jakarta.validation.Valid;

public interface TransactionService {

	TransactionEntity saveTransaction(@Valid TransactionEntity transactionEntity);

	List<TransactionEntity> getAllTransactions();

	List<TransactionEntity> getTransactionDetailsByAccountId(Long accountId);

}
