package com.in.pathshala.onlineLoginSignup.Dto.user;

public class SignInDto {

	private String email;
	private String password;
	
	public SignInDto() {}

	public SignInDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignInDto [email=" + email + ", password=" + password + "]";
	}
	
}
