package com.example.BankingApp.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionEntity saveTransaction(@Valid TransactionEntity transactionEntity) {
		return transactionRepository.save(transactionEntity);
	}

	@Override
	public List<TransactionEntity> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public List<TransactionEntity> getTransactionDetailsByAccountId(Long accountId) {
		return transactionRepository.findByAccountAccountId(accountId);
	}

}
