package com.in.pathshala.onlineBookStore.Dto.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
	public AuthenticationFailException(String msg) {
		super(msg);
	}
}

