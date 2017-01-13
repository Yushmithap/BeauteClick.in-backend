package com.niit.shopgirlbackend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CATEGORY")
@Component
public class Category {

	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@NotEmpty
	private String description;
	
	private Set<Product> product;
	
	@OneToMany(mappedBy="category" , fetch = FetchType.EAGER)
	public Set<Product> getProduct() {
		return product;
	}

	
	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
