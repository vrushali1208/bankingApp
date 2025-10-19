package com.example.BankingApp.Account;

public class AccountNumberGenerator {
	
	 // Generate account number using database ID
    public static String generateAccountNumber(Long id) {
        return String.format("AC%010d", id); // AC0000001234
    }

}
