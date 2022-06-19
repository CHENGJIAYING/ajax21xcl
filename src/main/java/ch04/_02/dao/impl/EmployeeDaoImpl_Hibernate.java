package ch04._02.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch04.Employee;
import ch04._02.service.impl.EmployeeDao;

@Repository
public class EmployeeDaoImpl_Hibernate implements EmployeeDao {
	
	SessionFactory factory;
	
	//@Autowired
	public EmployeeDaoImpl_Hibernate(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	@Override
	public void save(Employee e) {
		Session session = factory.getCurrentSession();
		session.save(e);
	}
	@Override
	public Employee findById(Long id) {
		Session session = factory.getCurrentSession();
		return session.get(Employee.class,id);
	
	}
	@Override
	public List<Employee> findALL() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Employee" ;
		 return session.createQuery(hql,Employee.class)
				 	   .getResultList();
		
	}
}
