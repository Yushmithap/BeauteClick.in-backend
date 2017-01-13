package com.niit.shopgirlbackend.dao;

import java.util.List;


import com.niit.shopgirlbackend.model.Product;



public interface ProductDAO {

	public List<Product> list();
	
	public Product getProductDetails(String hql);
	
	public Product get(String id);
	
	public Product getProductByName(String name);
	
	public boolean saveOrUpdate(Product product);
	
	public boolean delete(String id);
}
