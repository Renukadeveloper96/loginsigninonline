package com.in.pathshala.onlineBookStore.Dto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="cart")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    
    @JsonIgnore
    @OneToOne(targetEntity = Seller.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "seller_id")
    private Seller seller;
    
    private int quantity;

    public Cart() {}
    
    public Cart(Book book, int quantity, Seller seller){
        this.seller = seller;
        this.book = book;
        this.quantity = quantity;
        this.createdDate = new Date();
    }
	public Cart(Integer id, Date createdDate, Book book, Seller seller, int quantity) {
		this.id = id;
		this.createdDate = createdDate;
		this.book = book;
		this.seller = seller;
		this.quantity = quantity;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Cart [id=" + id + ", createdDate=" + createdDate + ", book=" + book + ", seller=" + seller
				+ ", quantity=" + quantity + "]";
	}
    
    
}
