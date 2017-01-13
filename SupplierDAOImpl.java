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

import com.niit.shopgirlbackend.dao.SupplierDAO;
import com.niit.shopgirlbackend.model.Supplier;


@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SupplierDAOImpl.class);
	
	public SupplierDAOImpl(){
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SupplierDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Supplier> list() {
		logger.debug("Starting of the method calliing list");
		@SuppressWarnings("unchecked")
		List<Supplier> list = (List<Supplier>) sessionFactory.getCurrentSession()
				.createCriteria(Supplier.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		logger.debug("Ending of the method calling list");
		return list();
	}
	
	@Transactional
	public Supplier getSupplierDetails(String hql){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Supplier> list = (List<Supplier>) query.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);	
		}
		return null;
	}
	
	@Transactional
	public Supplier get(String id) {
		logger.debug("start of the method : get");
		String hql = "from Supplier where id=" + "'"+id+"'";
		logger.debug("Ending of the method : get");
		return getSupplierDetails(hql);
	}
	
	@Transactional
	public Supplier getSupplierByName(String name){
		logger.debug("Starting of the method: getUserByName");
		String hql = "from Supplier where name="+"'"+name+"'";
		logger.debug("Ending of the method : getUserByName");
		return getSupplierDetails(hql);
	}
	
	@Transactional
	public boolean saveOrUpdate(Supplier supplier) {
		logger.debug("Starting of the method: save");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			logger.debug("Ending of the method: save");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception occured while saving Supplier");
			logger.error(e.getMessage());
			return false;
		}
		
	}
	
	@Transactional
	public boolean delete(String id){
		logger.debug("Starting of the method: delete");
		try {
			Supplier supplier = new Supplier();
			supplier.setId(id);
			sessionFactory.getCurrentSession().delete(supplier);
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

