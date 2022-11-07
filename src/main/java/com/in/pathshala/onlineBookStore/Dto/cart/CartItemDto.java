package com.in.pathshala.onlineBookStore.Dto.cart;


import com.in.pathshala.onlineBookStore.Dto.checkout.CheckoutItemDto;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Cart;


public class CartItemDto {
	 private long id;
	    private  long quantity;
	    private  Book book;

	    public CartItemDto() {
	    }

	    public CartItemDto(Cart cart) {
	        this.setId(cart.getId());
	        this.setQuantity(cart.getQuantity());
	        this.setBook(cart.getBook());
	    }

		@Override
		public String toString() {
			return "CartItemDto [id=" + id + ", quantity=" + quantity + ", book=" + book + "]";
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getQuantity() {
			return quantity;
		}

		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}	    
}
