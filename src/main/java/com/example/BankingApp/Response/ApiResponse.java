package com.example.BankingApp.Response;

public class ApiResponse<T> {
    private String message;
    private T data;
    private Boolean success;
    
	public ApiResponse(String message, T data, Boolean success) {
		super();
		this.message = message;
		this.data = data;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}   
}