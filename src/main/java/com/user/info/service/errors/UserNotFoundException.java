package com.user.info.service.errors;
public class UserNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String errorMessage;
	
	public UserNotFoundException(String message){
		super(message);
		this.errorMessage = message;
	}

}
