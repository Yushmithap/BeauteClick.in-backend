package com.niit.shopgirlbackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shopgirlbackend.dao.ProductDAO;
import com.niit.shopgirlbackend.model.Product;

import junit.framework.Assert;


public class ProductDAOTestCase {

	@Autowired
	static ProductDAO productDAO;
	
	@Autowired
	static Product product;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shopgirlbackend");
		context.refresh();
		
		product = (Product) context.getBean("product");
		productDAO  = (ProductDAO) context.getBean("productDAOImpl");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getProductTestCase()
	{
		product = productDAO.get("PROBODY01");
		Assert.assertNotNull("get product by id", product);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getProductTestCase2()
	{
		product = productDAO.getProductByName("MADARA milk product hair wash");
		Assert.assertNotNull("get product by name", product);
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void getAllCategoryTestCase(){
		int size = productDAO.list().size();
		Assert.assertEquals("get all products", 2, size);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void saveOrUpdateTest(){
		product.setProductID("PROBODY02");
		product.setName("Caprina Fresh Goats Milk Body Wash");
		product.setDescription("Caprina Fresh Goats Milk Body Wash is enriched with fresh goat milk");
		product.setCategoryID("BWS01");
		product.setSupplierID("SUPUS01");
		product.setPrice(1424);
		product.setSize("350ml");
		product.setStock(10);
		Assert.assertEquals("save Test Case", true, productDAO.saveOrUpdate(product));
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void deleteTest(){
		Assert.assertEquals("delete test case", true,productDAO.delete("PRO02"));
	}
	

}
