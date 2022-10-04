package com.in.pathshala.onlineLoginSignup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineLoginSignup.model.Product;
import com.in.pathshala.onlineLoginSignup.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProduct() {
		try {
			logger.info("requesting getAllproducts from ProductServiceImpl");
			return productRepository.findAll();
		}catch(Exception e) {
			logger.info("try catch block in ProductRepository");
			return null;
		}
		
	}

	@Override
	public Product findProductById(long id) {
		try {
			logger.info("requesting getAllproducts from ProductServiceImpl");
			return productRepository.findById(id).get();
	}catch(Exception e) {
		logger.info("try catch block in ProductRepository");
		return null;
	}
}

	@Override
	public Product saveProduct(Product product) {
		try {
			logger.info("requesting saveProducts from ProductServiceImpl");
			return productRepository.save(product);
	}catch(Exception e) {
		logger.info("try catch block in ProductRepository");
		return null;
	}
}

	@Override
	public Product updateProduct(Product product) {
		try {
			logger.info("requesting updateProducts from ProductServiceImpl");
			return productRepository.save(product);
	}catch(Exception e) {
		logger.info("try catch block updateProduct in ProductRepository");
		return null;
	}
	}

	@Override
	public Product deleteProductById(long id) {
		try {
			logger.info("requesting deleting Products from ProductServiceImpl");
			productRepository.deleteById(id);
	}catch(Exception e) {
		logger.info("try catch block delete Product in ProductRepository"+e);
		
	}
		return null;
}
}
