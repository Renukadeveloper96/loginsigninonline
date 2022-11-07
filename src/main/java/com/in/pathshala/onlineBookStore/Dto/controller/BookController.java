package com.in.pathshala.onlineBookStore.Dto.controller;

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

import com.in.pathshala.onlineBookStore.Dto.BaseResponseDTO;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.service.BookService;

@RestController
public class BookController {

	Logger logger=LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	public BookService bookService;
	
	@GetMapping(value="/getAllBook")
	public ResponseEntity<?> getAllBook(){
		logger.info("Requesting getAllBook from BookController");
		List<Book>result=bookService.getAllBook();
		if(result!=null) {
			logger.info("result of getAllBook from BookController is excecuted");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(true);
			responseDto.setHttpCode("200");
			responseDto.setData(result);
			return ResponseEntity.ok(responseDto);
		}
		else {
			logger.error("GetAllBook list is null");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(false);
			responseDto.setHttpCode("400");
			responseDto.setData(result);
			return ResponseEntity.badRequest().body(responseDto);
		}
	}
	@GetMapping(value="/getAllBook/{id}")
	public ResponseEntity<?> findBookById(@PathVariable("id") long id){
		logger.info("Requesting getbookByID from book controller");
		Book result =bookService.findBookById(id);
		if(result!=null) {
			logger.info("result of findBookById from book controller is executed");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(true);
			responseDto.setHttpCode("200");
			responseDto.setData(result);
			return ResponseEntity.ok(responseDto);
		}else {
			logger.error("GetBookById list is null");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(false);
			responseDto.setHttpCode("400");
			responseDto.setData(result);
			return ResponseEntity.badRequest().body(responseDto);
		}
		
	}
	@PostMapping(value="/createBook")
	public ResponseEntity<?>saveBook(@RequestBody Book book){
		logger.info("Requesting savebook from book controller");
		Book result =bookService.saveBook(book);
		if(result!=null) {
			logger.info("result of saveBook from book controller is executed");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(true);
			responseDto.setHttpCode("200");
			responseDto.setData(result);
			return ResponseEntity.ok(responseDto);
		}
		else {
			logger.error("save Book list is null");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(false);
			responseDto.setHttpCode("400");
			responseDto.setData(result);
			return ResponseEntity.badRequest().body(responseDto);
		}
	}
	@PutMapping(value="/updateBook")
	public ResponseEntity<?>updateBook (@RequestBody Book bookRequest){
//		logger.info("User Id coming from Request::" + bookRequest.getBook_id());
		Book result=bookService.findBookById(bookRequest.getId());
		if(result!=null) {
		logger.info("result of UpdateBook is executed");
		result.setName(bookRequest.getName());
		result.setLanguange_name(bookRequest.getLanguange_name());
		result.setNum_pages(bookRequest.getNum_pages());
		result.setPublisher_id(bookRequest.getPublisher_id());
		result.setPublisher_name(bookRequest.getPublisher_name());
		result.setAuthor_id(bookRequest.getAuthor_id());
		result.setAuthor_name(bookRequest.getAuthor_name());
		result.setNum_pages(bookRequest.getNum_pages());
		result=bookService.saveBook(result);
		BaseResponseDTO responseDto=new BaseResponseDTO();
		responseDto.setError(true);
		responseDto.setHttpCode("200");
		responseDto.setData(result);
		return ResponseEntity.ok(responseDto);
	}
		else {
			logger.error("GetBookById list is null");
			BaseResponseDTO responseDto=new BaseResponseDTO();
			responseDto.setError(false);
			responseDto.setHttpCode("400");
			responseDto.setData(result);
			return ResponseEntity.badRequest().body(responseDto);
	}
	}
	@DeleteMapping("/deleteBook/{id}")
	public void deleteProduct(@PathVariable long id) {
	 logger.info("Deleting by id is executed");
	 bookService.deleteBookById(id);
}
}
