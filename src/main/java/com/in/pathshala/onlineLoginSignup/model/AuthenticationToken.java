package com.in.pathshala.onlineLoginSignup.model;



import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tokens")
public class AuthenticationToken {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	private String token;
	
	@Column(name="create_date")
	private Date createdDate;
	
	@OneToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name ="user_id")
	private User user;
	
	public AuthenticationToken() {}
	public AuthenticationToken(User user) {
		this.user=user;
		this.createdDate=new Date();
		this.token=UUID.randomUUID().toString();
	}
	
	public AuthenticationToken(long id, String token, Date createdDate, User user) {
		super();
		this.id = id;
		this.token = token;
		this.createdDate = createdDate;
		this.user = user;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthenticationToken [id=" + id + ", token=" + token + ", createdDate=" + createdDate + ", user=" + user
				+ "]";
	}
	
	
	
}
