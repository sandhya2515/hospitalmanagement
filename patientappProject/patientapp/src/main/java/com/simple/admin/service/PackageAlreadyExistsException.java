package com.simple.admin.service;

public class PackageAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PackageAlreadyExistsException(String message) {
        super(message);
    }

}
