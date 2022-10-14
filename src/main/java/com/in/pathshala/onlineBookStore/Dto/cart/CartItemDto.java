package com.in.pathshala.onlineBookStore.Dto.cart;

import javax.validation.constraints.NotNull;

import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Cart;


public class CartItemDto {
	 private Integer id;
	    private @NotNull Integer quantity;
	    private @NotNull Book book;

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

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

	   
	    
}
