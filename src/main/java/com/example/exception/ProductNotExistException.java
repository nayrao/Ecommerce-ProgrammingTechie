package com.example.exception;

public class ProductNotExistException extends IllegalArgumentException {
	
	public ProductNotExistException(String msg) {
		super(msg);
	}

}
