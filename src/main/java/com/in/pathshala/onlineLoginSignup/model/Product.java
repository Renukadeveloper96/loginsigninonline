package com.in.pathshala.onlineLoginSignup.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private long id;

    private  String name;
    private  String imageURL;
    private  double price;
    private  String description;

    public Product() {}

    
	public Product(long id, String name, String imageURL, double price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
		this.description = description;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", imageURL=" + imageURL + ", price=" + price + ", description="
				+ description + "]";
	}


	public void saveProduct(Product updateProduct) {
		// TODO Auto-generated method stub
		
	}

 
}
