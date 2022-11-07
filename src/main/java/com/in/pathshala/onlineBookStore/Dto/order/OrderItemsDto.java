package com.in.pathshala.onlineBookStore.Dto.order;

public class OrderItemsDto {

    private  double price;
    private  long quantity;
    private  long orderId;
    
    private  long bookId;

    public OrderItemsDto () {}

	public OrderItemsDto(double price, long quantity, long orderId, long bookId) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.orderId = orderId;
		this.bookId = bookId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
}
