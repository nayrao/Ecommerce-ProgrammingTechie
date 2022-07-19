package com.example.dto;

import java.util.List;

public class CartDtoGet {
	
	private List<CartItemDto> cartItems;
	private double totalPrice;
	
	public CartDtoGet() {
		// TODO Auto-generated constructor stub
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

}
