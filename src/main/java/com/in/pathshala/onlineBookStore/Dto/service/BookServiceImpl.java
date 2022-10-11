package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
//	Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	Logger logger=LoggerFactory.getLogger(BookServiceImpl.class);
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBook() {
		try {
			logger.info("requesting getAllBook from BookServiceImpl");
			return bookRepository.findAll();
		}catch(Exception e) {
			logger.error("try catch block in getAllBook from BookServiceImpl");
			return null;
		}
	}

	@Override
	public Book findBookById(long id) {
		try {
			logger.info("requesing findBookById from BookServiceImpl");
			return bookRepository.findById(id).get();
		}catch(Exception e) {
			logger.error("try catch block in findBookById from BookServiceImp");
			return null;
		}
	}

	@Override
	public Book saveBook(Book book) {
		try {
			logger.info("requesting saveBook from BookServiceImpl");
			return bookRepository.save(book);
		}catch(Exception e) {
			logger.error("try catch block in saveBook from BookSeviceImpl");
			return null;
		}
	}

	@Override
	public Book updateBook(Book book) {
		try {
			logger.info("requesting updateBook from BookServiceImpl");
			return bookRepository.save(book);
		}catch(Exception e) {
			logger.error("try catch block in updateBook from BookServiceImpl");
			return null;
		}
	}

	@Override
	public void deleteBookById(long id) {
			logger.info("requesting deleteBookById from BookServiceImpl");
			bookRepository.deleteById(id);
	}

}
