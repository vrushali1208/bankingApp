package com.example.BankingApp.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public AccountEntity saveAccount(@Valid AccountEntity accountEntity) {
		return accountRepository.save(accountEntity);
	}

	@Override
	public Optional<AccountEntity> findById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	@Override
	public List<AccountEntity> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public List<AccountEntity> getAccountDetailsByCustomer(Long customerId) {
		return accountRepository.findByCustomerCustomerId(customerId);
	}
	
	

}
