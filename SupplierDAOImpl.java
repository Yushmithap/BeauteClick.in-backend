package com.niit.shopgirlbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shopgirlbackend.dao.SupplierDAO;
import com.niit.shopgirlbackend.model.Supplier;

@Repository
public class SupplierDAOImpl implements SupplierDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	public SupplierDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	

	@Transactional
	public List<Supplier> list() {
		String hql = "from Supplier";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Transactional
	public Supplier get(String id) {
		return (Supplier) sessionFactory.getCurrentSession().get(Supplier.class, id);
	}
	
	
	
	@Transactional
	public boolean save(Supplier supplier) {
		
		try {
			sessionFactory.getCurrentSession().save(supplier);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Transactional
	public boolean update(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().update(supplier);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
