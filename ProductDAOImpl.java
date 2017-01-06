package com.niit.shopgirlbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shopgirlbackend.dao.ProductDAO;
import com.niit.shopgirlbackend.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Product> list() {
		String hql = "from Product";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Transactional
	public Product get(String id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}
	
	@Transactional
	public boolean save(Product product) {

		try {
			sessionFactory.getCurrentSession().save(product);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Transactional
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
}
