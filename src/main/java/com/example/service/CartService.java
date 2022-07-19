package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CartDto;
import com.example.dto.CartDtoGet;
import com.example.dto.CartItemDto;
import com.example.exception.CustomException;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartRepository cartRepository;

	public void addToCart(CartDto cartDto, User user) {
		//validate if the product id is valid
		Product findByIdProduct = productService.findById(cartDto.getProductId());
		
		Cart cart=new Cart();
		cart.setProduct(findByIdProduct);
		cart.setUser(user);
		cart.setQuantity(cartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		cartRepository.save(cart);
	}

	public CartDtoGet listCartItem(User user) {
		
		List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		
		List<CartItemDto> cartItems=new ArrayList<>();
		double totalCost=0;
		
		for(Cart cart:cartList) {
			CartItemDto cartItemDto=new CartItemDto(cart);
			totalCost+=cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		
		CartDtoGet cartDtoGet=new CartDtoGet();
		cartDtoGet.setTotalPrice(totalCost);
		cartDtoGet.setCartItems(cartItems);
		return cartDtoGet;
	}

	public void deleteCartItem(Integer cartItemId, User user) {
		
		//check Item Id is belong to user or not
		Optional<Cart> optionalCart=cartRepository.findById(cartItemId);
		
		if(optionalCart.isEmpty()) {
			throw new CustomException("Cart Item ID is invalid::"+cartItemId);
		}
		
		Cart cart = optionalCart.get();
		if(cart.getUser() != user) {
			throw new CustomException("Cart Item Does not belongs to User::"+cartItemId);
		}
		cartRepository.delete(cart);
	}

}
