package com.niit.shopgirlbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shopgirlbackend.dao.UserDAO;
import com.niit.shopgirlbackend.model.User;



@Repository
public  class UserDAOImpl implements UserDAO {
	//creating a session so that connection loads and all hibernate configurations loads so that I can write HQL.
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<User> list() {
		String hql="from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	//if the valid id --> will return domain object
	//if the invalid id --> will return null
	@Transactional
	public User get(String id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
	@Transactional
	public User validate(String id, String password) {
		String hql="from User where id='" + id + "'and password='" +password+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return (User)query.uniqueResult();
	}
	@Transactional
	public boolean save(User user) {
		// TODO Auto-generated method stub
		//This method is used for the purpose of any registrations
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Transactional
	public boolean update(User user) {
		// TODO Auto-generated method stub
		
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	


	
}