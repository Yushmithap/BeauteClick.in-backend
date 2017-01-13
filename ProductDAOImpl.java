package com.niit.shopgirlbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shopgirlbackend.dao.ProductDAO;
import com.niit.shopgirlbackend.model.Product;


@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	public ProductDAOImpl(){
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Product> list() {
		logger.debug("Starting of the method calliing list");
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		logger.debug("Ending of the method calling list");
		return list();
	}
	
	@Transactional
	public Product getProductDetails(String hql){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) query.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);	
		}
		return null;
	}
	
	@Transactional
	public Product get(String productID) {
		logger.debug("start of the method : get");
		String hql = "from Product where productID=" + "'"+productID+"'";
		logger.debug("Ending of the method : get");
		return getProductDetails(hql);
	}
	
	@Transactional
	public Product getProductByName(String name){
		logger.debug("Starting of the method: getUserByName");
		String hql = "from Product where name="+"'"+name+"'";
		logger.debug("Ending of the method : getUserByName");
		return getProductDetails(hql);
	}
	
	@Transactional
	public boolean saveOrUpdate(Product product) {
		logger.debug("Starting of the method: saveOrUpdate");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			logger.debug("Ending of the method: saveOrUpdate");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception occured while saving or updating Product");
			logger.error(e.getMessage());
			return false;
		}
		
	}
	
	
		
	
	@Transactional
	public boolean delete(String productID){
		logger.debug("Starting of the method: delete");
		try {
			Product product = new Product();
			product.setProductID("PRO01");
			sessionFactory.getCurrentSession().delete(product);
			logger.debug("Ending of the method: delete");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Not able to delete the record"+e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	
}
