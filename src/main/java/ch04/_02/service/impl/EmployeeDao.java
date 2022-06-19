package ch04._02.service.impl;

import java.util.List;

import ch04.Employee;

public interface EmployeeDao {

	void save(Employee e);
	Employee findById(Long id);
	List<Employee> findALL();

}
