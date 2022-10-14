package com.in.pathshala.onlineBookStore.Dto.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class  Book{
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
@Column(name="id")
private long id;
private String name;
private String languange_name;
private String num_pages;
private long publisher_id;
private String publisher_name;
private String publication_date;
private long author_id;
private String author_name;
private String description;
private int price;
public Book() {
//	this.publication_date=new Date();
}
public Book(long id, String name, String languange_name, String num_pages, long publisher_id,
		String publisher_name, String publication_date, long author_id, String author_name, String description,
		int price) {
	super();
	this.id = id;
	this.name = name;
	this.languange_name = languange_name;
	this.num_pages = num_pages;
	this.publisher_id = publisher_id;
	this.publisher_name = publisher_name;
	this.publication_date = publication_date;
	this.author_id = author_id;
	this.author_name = author_name;
	this.description = description;
	this.price = price;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLanguange_name() {
	return languange_name;
}
public void setLanguange_name(String languange_name) {
	this.languange_name = languange_name;
}
public String getNum_pages() {
	return num_pages;
}
public void setNum_pages(String num_pages) {
	this.num_pages = num_pages;
}
public long getPublisher_id() {
	return publisher_id;
}
public void setPublisher_id(long publisher_id) {
	this.publisher_id = publisher_id;
}
public String getPublisher_name() {
	return publisher_name;
}
public void setPublisher_name(String publisher_name) {
	this.publisher_name = publisher_name;
}
public String getPublication_date() {
	return publication_date;
}
public void setPublication_date(String publication_date) {
	this.publication_date = publication_date;
}
public long getAuthor_id() {
	return author_id;
}
public void setAuthor_id(long author_id) {
	this.author_id = author_id;
}
public String getAuthor_name() {
	return author_name;
}
public void setAuthor_name(String author_name) {
	this.author_name = author_name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
@Override
public String toString() {
	return "Book [book_id=" + id + ", name=" + name + ", languange_name=" + languange_name + ", num_pages="
			+ num_pages + ", publisher_id=" + publisher_id + ", publisher_name=" + publisher_name
			+ ", publication_date=" + publication_date + ", author_id=" + author_id + ", author_name=" + author_name
			+ ", description=" + description + ", price=" + price + "]";
}

}