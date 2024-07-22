package com.deyvidsalvatore.web.expensifyapi.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.models.Reviewer;
import com.deyvidsalvatore.web.expensifyapi.repositories.EmployeeRepository;
import com.deyvidsalvatore.web.expensifyapi.repositories.ReviewerRepository;

@Service
public class ExpensifyUserService implements UserDetailsService {
	
	private final EmployeeRepository employeeRepository;
	private final ReviewerRepository reviewerRepository;

	public ExpensifyUserService(EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository) {
		this.employeeRepository = employeeRepository;
		this.reviewerRepository = reviewerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employee = this.employeeRepository.findByUsername(username);
		if (employee.isPresent()) {
			return employee.get();
		}
		
		Optional<Reviewer> reviewer = reviewerRepository.findByUsername(username);
		if (reviewer.isPresent()) {
			return reviewer.get();
		}
		
		throw new UsernameNotFoundException(username);
	}

}
