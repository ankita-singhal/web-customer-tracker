package com.java.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject the session factory 
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create the query.. sort by last name 
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomers(int theId) {
				//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//retrieve /read from database using the primary key 
				Customer theCustomer = currentSession.get(Customer.class, theId);
				
				//return the result
				return theCustomer;
	}

	@Override
	public void deleteCustomer(int theID) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with promary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theID);
		
		theQuery.executeUpdate();
		
	}

}
