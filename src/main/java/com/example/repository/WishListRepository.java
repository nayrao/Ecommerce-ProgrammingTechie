package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
