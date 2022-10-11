package com.in.pathshala.onlineBookStore.Dto.Seller;

public class SignupDto {
private String firstName;
private String lastName;
private String email;
private String password;
private String address;
private String phone;
public SignupDto() {}
public SignupDto(String firstName, String lastName, String email, String password, String address, String phone) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.address = address;
	this.phone = phone;
}

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
@Override
public String toString() {
	return "SignupDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
			+ password + ", address=" + address + ", phone=" + phone + "]";
}


}