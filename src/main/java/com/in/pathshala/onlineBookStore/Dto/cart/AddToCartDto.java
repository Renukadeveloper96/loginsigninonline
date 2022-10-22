package com.in.pathshala.onlineBookStore.Dto.cart;

import javax.validation.constraints.NotBlank;

public class AddToCartDto {
        @NotBlank
	    private   Integer bookId;
	    private  Integer quantity;
	    private Integer sellerId;
	    private Double cartAmount;
	    public AddToCartDto() {
	    }
	    


		public Integer getSellerId() {
			return sellerId;
		}



		public void setSellerId(Integer sellerId) {
			this.sellerId = sellerId;
		}



		public Integer getBookId() {
			return bookId;
		}

		public void setBookId(Integer bookId) {
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
