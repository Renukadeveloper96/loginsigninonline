package com.in.pathshala.onlineBookStore.Dto.cart;

import javax.validation.constraints.NotBlank;

public class AddToCartDto {
        @NotBlank
	    private   long bookId;
	    private  Integer quantity;
	    private long sellerId;
	    private Double cartAmount;
	    public AddToCartDto() {
	    }
	    
		public long getSellerId() {
			return sellerId;
		}

		public void setSellerId(long sellerId) {
			this.sellerId = sellerId;
		}

		public long getBookId() {
			return bookId;
		}

		public void setBookId(long bookId) {
			this.bookId = bookId;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
		public Double getCartAmount() {
			return cartAmount;
		}



		public void setCartAmount(Double cartAmount) {
			this.cartAmount = cartAmount;
		}



		@Override
		public String toString() {
			return "AddToCartDto [bookId=" + bookId + ", quantity=" + quantity + ", sellerId=" + sellerId
					+ ", cartAmount=" + cartAmount + "]";
		}



		

		


}
