package com.deyvidsalvatore.web.expensifyapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.models.Expense;
import com.deyvidsalvatore.web.expensifyapi.repositories.EmployeeRepository;
import com.deyvidsalvatore.web.expensifyapi.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	
	private final EmployeeRepository employeeRepository;
	private final ExpenseRepository expenseRepository;
	
	public ExpenseService(EmployeeRepository employeeRepository, ExpenseRepository expenseRepository) {
		this.employeeRepository = employeeRepository;
		this.expenseRepository = expenseRepository;
	}

	public Optional<Expense> findById(Integer id) {
		return this.expenseRepository.findById(id);
	}
	
	public Expense addOneExpense(Employee employee, Expense expense) {
		Expense expenseWithId = this.expenseRepository.save(expense);
		employee.getExpenses().add(expense);
		this.employeeRepository.save(employee);
		return expenseWithId;
	}
	
	public void deleteOne(Employee employee, Expense expense) {
		employee.getExpenses().remove(expense);
		this.employeeRepository.save(employee);
	}

}
