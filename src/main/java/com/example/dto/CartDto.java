package com.example.dto;

import javax.validation.constraints.NotNull;

public class CartDto {
	private Integer id;
	@NotNull
	private Integer productId;
	@NotNull
	private Integer quantity;
	private Integer amount;
	private String village;

	public CartDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	

}
