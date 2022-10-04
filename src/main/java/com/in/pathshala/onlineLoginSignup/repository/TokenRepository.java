package com.in.pathshala.onlineLoginSignup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pathshala.onlineLoginSignup.model.AuthenticationToken;
import com.in.pathshala.onlineLoginSignup.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Long>{
AuthenticationToken findByUser(User user);

AuthenticationToken findTokenByToken(String token);
}
