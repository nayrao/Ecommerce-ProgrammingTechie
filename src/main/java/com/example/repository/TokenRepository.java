package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AuthenticationToken;
import com.example.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{

	AuthenticationToken findByUser(User findByEmail);

	AuthenticationToken findByToken(String token);

}
