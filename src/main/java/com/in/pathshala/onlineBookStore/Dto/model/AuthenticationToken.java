package com.in.pathshala.onlineBookStore.Dto.model;

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
	
	@OneToOne(targetEntity=Seller.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name ="seller_id")
	private Seller seller;
	public AuthenticationToken() {}
	public AuthenticationToken(Seller seller) {
		this.seller=seller;
		this.createdDate=new Date();
		this.token=UUID.randomUUID().toString();
	}
	public AuthenticationToken(long id, String token, Date createdDate, Seller seller) {
		super();
		this.id = id;
		this.token = token;
		this.createdDate = createdDate;
		this.seller = seller;
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
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	@Override
	public String toString() {
		return "AuthenticationToken [id=" + id + ", token=" + token + ", createdDate=" + createdDate + ", seller="
				+ seller + "]";
	}
	
}
