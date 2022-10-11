package com.in.pathshala.onlineBookStore.Dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pathshala.onlineBookStore.Dto.model.AuthenticationToken;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Long>{
	AuthenticationToken findBySeller(Seller seller);
	AuthenticationToken findTokenByToken(String token);
}
