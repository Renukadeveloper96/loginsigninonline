package com.in.pathshala.onlineLoginSignup.service;

import java.util.List;

import com.in.pathshala.onlineLoginSignup.model.Product;

public interface ProductService {

	public List<Product>getAllProduct();
	public Product findProductById(long id);
	public Product  saveProduct(Product product);
	public Product updateProduct(Product product);
	public Product deleteProductById(long id);
//	public Product delete(long id);
		
}
