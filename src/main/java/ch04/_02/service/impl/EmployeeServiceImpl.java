package ch04._02.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch04.Employee;
import ch04._02.service.EmployeeService;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao dao;
	
	
	//@Autowired
	public EmployeeServiceImpl( EmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public void save(Employee e) {
		dao.save(e);
	}

	@Override
	public Employee findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Employee> findALL() {
		return dao.findALL();
	}

}
