package com.example.BankingApp.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public interface AccountService{

	AccountEntity saveAccount(@Valid AccountEntity accountEntity);

	Optional<AccountEntity> findById(Long accountId);

	List<AccountEntity> getAllAccounts();

	List<AccountEntity> getAccountDetailsByCustomer(Long customerId);

	void deleteAccountById(Long accountId);

}
