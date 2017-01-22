package com.niit.shopgirlbackend;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shopgirlbackend.dao.CartDAO;
import com.niit.shopgirlbackend.model.Cart;

import junit.framework.Assert;

public class CartDAOTestCase {

	@Autowired
	static CartDAO cartDAO;
	
	@Autowired
	static Cart cart;
	
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shopgirlbackend");
		context.refresh();
		
		cart = (Cart) context.getBean("cart");
		cartDAO  = (CartDAO) context.getBean("cartDAOImpl");
	}
	
	@Test
	public void getAllCartTestCase(){
		
		int size = cartDAO.list("niit").size();
		Assert.assertEquals("get all cart items", 1, size);
	}
	
	@Test
	public void deleteCartTest(){
		Assert.assertEquals("delete test case", true, cartDAO.delete(cart.getId()));
	}
	
	@Test
	public void getTotalTestCase(){
		
		Double total = cartDAO.getTotalAmount("Yushmitha6a1a");
	Assert.assertNotNull("Total amount", total);
	}
	
	
	@Test
	public void saveOrUpdateCartTest(){
		cart.setId(01);
		cart.setProductName("CRAZY COLOR Burgundy Hair Colour");
		cart.setUserID("Yushmitha6a1a");
		cart.setPrice(585);
		cart.setQuantity(5);
		cart.setStatus("NEW");
		cart.getDateAdded();
		Assert.assertEquals("save Test Case", true, cartDAO.saveOrUpdate(cart));
	}
	
	
	
	
	
	
	
}
