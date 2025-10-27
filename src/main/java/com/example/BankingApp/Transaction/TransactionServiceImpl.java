package com.example.BankingApp.Transaction;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<TransactionEntity> findById(Long transactionId) {
		return transactionRepository.findById(transactionId);
	}

	@Override
	public void deleteTransactionById(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

}
