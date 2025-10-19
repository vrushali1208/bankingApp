package com.example.BankingApp;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
