package com.in.pathshala.onlineBookStore.Dto.cart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddToCartDto {
        @NotBlank
	    private   Integer bookId;
	    private  Integer quantity;
	    private Integer sellerId;
	    
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



		@Override
		public String toString() {
			return "AddToCartDto [bookId=" + bookId + ", quantity=" + quantity + ", sellerId=" + sellerId + "]";
		}

		


}
