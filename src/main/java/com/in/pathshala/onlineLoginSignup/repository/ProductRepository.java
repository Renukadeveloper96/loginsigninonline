package com.in.pathshala.onlineLoginSignup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pathshala.onlineLoginSignup.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
