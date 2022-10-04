package com.in.pathshala.onlineLoginSignup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.in.pathshala.onlineLoginSignup.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	List<User> findAll();
    User findByEmail(String email);
    User findUserByEmail(String email);
    User findUserById(long id);
//    User  saveUser(User User);
//    User updateUser(User user);
}
