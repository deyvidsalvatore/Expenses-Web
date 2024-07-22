package com.deyvidsalvatore.web.expensifyapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Optional<Employee> findByUsername(String username);
}
