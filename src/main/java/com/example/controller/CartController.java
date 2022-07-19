package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.ApiResponse;
import com.example.dto.CartDto;
import com.example.dto.CartDtoGet;
import com.example.model.User;
import com.example.service.AuthenticationService;
import com.example.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	//post cart api
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody CartDto cartDto,@RequestParam("token") String token){
		//autheticate the token
		authenticationService.authenticate(token);
		//find the user 
		User user = authenticationService.getUser(token);
		
		cartService.addToCart(cartDto,user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Product added to cart"),HttpStatus.CREATED);
		
	}
	
	//get all cart Items for a user
	
	@GetMapping("/")
	public ResponseEntity<CartDtoGet> getCartItems(@RequestParam("token") String token){
		
		//autheticate the token
		authenticationService.authenticate(token);
		//find the user 
		User user = authenticationService.getUser(token);

		CartDtoGet cartDto=cartService.listCartItem(user);
		return new ResponseEntity<>(cartDto,HttpStatus.OK);
	}
	
	//delete a cart item for user
	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId,
			                                          @RequestParam("token") String token){
		
		       //autheticate the token
				authenticationService.authenticate(token);
				//find the user 
				User user = authenticationService.getUser(token);
				
				cartService.deleteCartItem(cartItemId,user);
				
				return new ResponseEntity<>(new ApiResponse(true, "Item has been removed!"),HttpStatus.OK);
	}
}
