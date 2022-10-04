package com.in.pathshala.onlineLoginSignup.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.in.pathshala.onlineLoginSignup.Dto.BaseResponseDTO;
import com.in.pathshala.onlineLoginSignup.model.Product;
import com.in.pathshala.onlineLoginSignup.service.ProductService;
@RestController
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	public ProductService productService;
	
	//getAllProduct:localhost:8085/getAllProduct
	@GetMapping(value="/getAllProduct")
	public ResponseEntity<?> getAllProducts(){
		logger.info("Request has entered getAllStudents endpoint (pusing code from vs code)");
		List<Product> result= productService.getAllProduct();
		if(result!=null) {
			logger.info("result of getAllProduct is executed");
			BaseResponseDTO responseDTO = new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("200");
			responseDTO.setData(result);
			return ResponseEntity.ok(responseDTO);
		}else {
			logger.error("GetAll Product list is null");
			BaseResponseDTO responseDTO = new BaseResponseDTO();
			responseDTO.setError(true);
			responseDTO.setHttpCode("400");
			responseDTO.setData("There is some problem in getting the data");
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}	
	//getProductId:localhost:8085/getAllProduct/1
	@GetMapping(value="/getAllProduct/{id}")
	public ResponseEntity<?>retrieveUser(@PathVariable("id") long id) {
	logger.info("Request has entered getStudent ");
	Product product=productService.findProductById(id);
	if(product!=null) {
		logger.info("result of getProduct is executed");
		BaseResponseDTO responseDTO=new BaseResponseDTO();
		responseDTO.setError(false);
		responseDTO.setHttpCode("200");
		responseDTO.setData(product);
		return ResponseEntity.ok(responseDTO);
	}else {
		logger.error("Product id list");
		BaseResponseDTO responseDTO=new BaseResponseDTO();
		responseDTO.setError(true);
		responseDTO.setHttpCode("400");
		responseDTO.setData("There is some problem in getting the data");
		return ResponseEntity.badRequest().body(responseDTO);
	}
		
	}
	//saveProduct:localhost:8085/saveProduct
	@PostMapping("/saveProduct")
	public  ResponseEntity<?> saveProduct(@RequestBody Product product) {
		logger.info("Posting values from saveProduct");
		Product saveProduct=productService.saveProduct(product);
		if(saveProduct!=null) {
			logger.info("result of saveProduct is executed");
			BaseResponseDTO responseDTO=new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("200");
			responseDTO.setData(saveProduct);
			return ResponseEntity.ok(responseDTO);
			
		}
//		return studentService.saveStudent(student);
		else {
			logger.error("students values are posting");
			BaseResponseDTO responseDTO=new BaseResponseDTO();
			responseDTO.setError(true);
			responseDTO.setHttpCode("400");
			responseDTO.setData("There is some problem in getting the save data");
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	//updating Products:localhost:8085/updateProduct
	//(we should add id in body)
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateStudent(@RequestBody Product productRequest) {
		logger.info("User Id coming from Request::" + productRequest.getId());
		Product updateProduct = productService.findProductById(productRequest.getId());
		if (updateProduct != null) {
			logger.info("result of UpdateStudent is executed");
			updateProduct.setId(productRequest.getId());
			updateProduct.setName(productRequest.getName());
			updateProduct.setImageURL(productRequest.getImageURL());
			updateProduct.setPrice(productRequest.getPrice());
			updateProduct.setDescription(productRequest.getDescription());
			updateProduct=productService.saveProduct(updateProduct);
			BaseResponseDTO responseDTO =new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("200");
			responseDTO.setData(updateProduct);
			return ResponseEntity.ok(responseDTO);
		} else {
			logger.error("update students values " );
			BaseResponseDTO responseDTO=new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("400");
			responseDTO.setData("There is some problem in update");
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
////	//deleteProduct:localhost:8085/deleteProduct/4
////	@DeleteMapping("/deleteProduct/{id}")
////	public ResponseEntity<?> deleteProduct(@PathVariable long id) {
////	 logger.info("Deleting by id is executed");
////	 Product product =productService.deleteProductById(id);
//////	 if(product!=null) {
////	 return ResponseEntity.ok("Product deleted");
//////	}else {
//////		ResponseEntity.badRequest().body("user not found");
////////		return null;
//////	}
//	
//}
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable long id) {
		logger.info("Deleting by id is executed");
	 
		Product product =productService.deleteProductById(id);

		if (product != null) {
			logger.info("result of deletebyId is executed");
			productService.deleteProductById(id);
			BaseResponseDTO responseDTO=new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("200");
			responseDTO.setData(product);
			return ResponseEntity.ok(responseDTO);
		} else {
			logger.error("deleting by ids values");
			BaseResponseDTO responseDTO=new BaseResponseDTO();
			responseDTO.setError(false);
			responseDTO.setHttpCode("400");
			responseDTO.setData("There is some problem in deletebyid");
		return ResponseEntity.badRequest().body("User not found");
		}
	}
}
