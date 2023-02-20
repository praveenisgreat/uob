package com.ms.core.db.exceptions;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String name) {
		super("Could not find candidate " + name);
	}
	
	public CustomerNotFoundException(Long id) {
		super("Could not find candidate " + id);
	}
}
