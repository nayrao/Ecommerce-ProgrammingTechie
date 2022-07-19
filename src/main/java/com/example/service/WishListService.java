package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.WishList;
import com.example.repository.WishListRepository;

@Service
public class WishListService {
	
	@Autowired
	WishListRepository wishListRepository;

	public void createWishList(WishList wishList) {
		
		wishListRepository.save(wishList);
	}

}
