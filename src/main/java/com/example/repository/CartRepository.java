package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Cart;
import com.example.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

 	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

	Optional<Cart> findById(Integer cartItemId);

}
