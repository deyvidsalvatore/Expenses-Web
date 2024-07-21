package com.deyvidsalvatore.web.expensifyapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Iterable<Employee> findAll() {
		return this.employeeRepository.findAll();
	}
	
	public Optional<Employee> findById(Integer id) {
		return this.employeeRepository.findById(id);
	}

}
