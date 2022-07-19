package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.ApiResponse;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.WishList;
import com.example.service.AuthenticationService;
import com.example.service.WishListService;

@RestController
@RequestMapping("/wishList")
public class WishListController {
	
	@Autowired
	WishListService wishListService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	//Save product as wishList item
	
	@PostMapping("/addWishList")
	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,@RequestParam("token") String token){
		
	//authenticate the token
	authenticationService.authenticate(token);
	
	//find the user
	
	User user =authenticationService.getUser(token);
	//save the item in wishlist
	
	WishList wishList=new WishList(user,product);
	
	wishListService.createWishList(wishList);
	
	return new ResponseEntity<>(new ApiResponse(true, "added new wiahList"),HttpStatus.CREATED); 

      }
}
