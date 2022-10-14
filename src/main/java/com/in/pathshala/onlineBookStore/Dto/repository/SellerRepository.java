package com.in.pathshala.onlineBookStore.Dto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pathshala.onlineBookStore.Dto.model.Seller;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>{

	List<Seller> findAll();
    Seller findByEmail(String email);
    Seller findSellerByEmail(String email);
    Seller findSellerById(long id);
    public void deleteSellerById(long id);
    
	//Optional<Seller> findById(Integer sellerId);
}
