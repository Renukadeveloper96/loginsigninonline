package com.in.pathshala.onlineBookStore.Dto.order;


public class OrderDto {
    private long id;
    private  long sellerId;

    public OrderDto() {
    }

	public OrderDto(long id,  long sellerId) {
		super();
		this.id = id;
		this.sellerId = sellerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	
}
