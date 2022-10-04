package com.in.pathshala.onlineLoginSignup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pathshala.onlineLoginSignup.model.User;

public interface SignUpAllUserRepository extends JpaRepository<User,Long>{

}
