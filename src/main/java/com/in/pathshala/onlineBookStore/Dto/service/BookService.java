package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.List;

import com.in.pathshala.onlineBookStore.Dto.model.Book;

public interface BookService {

	public List<Book>getAllBook();
	public Book findBookById(long id);
	public Book saveBook(Book book);
	public Book updateBook(Book book);
	public void deleteBookById(long id);
	
}
