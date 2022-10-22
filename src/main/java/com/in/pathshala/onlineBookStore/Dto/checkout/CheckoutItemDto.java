package com.in.pathshala.onlineBookStore.Dto.checkout;

public class CheckoutItemDto {

	private String bookName;
	private long quantity;
	private double price;
	private long bookId;
	private long sellerId;

	public CheckoutItemDto(String bookName, long quantity, double price, long bookId, long sellerId) {
		this.bookName = bookName;
		this.quantity = quantity;
		this.price = price;
		this.bookId = bookId;
		this.sellerId = sellerId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

}
