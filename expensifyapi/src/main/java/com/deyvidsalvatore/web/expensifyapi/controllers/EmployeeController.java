package com.deyvidsalvatore.web.expensifyapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.models.Expense;
import com.deyvidsalvatore.web.expensifyapi.services.EmployeeService;
import com.deyvidsalvatore.web.expensifyapi.services.ExpenseService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	private final ExpenseService expenseService;
	
	public EmployeeController(EmployeeService employeeService, ExpenseService expenseService) {
		this.employeeService = employeeService;
		this.expenseService = expenseService;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Employee>> findAllEmployees() {
		return ResponseEntity.ok(employeeService.findAll());
	}
	
	@GetMapping("/{employee_id}/expenses")
	public ResponseEntity<Iterable<Expense>> findExpensesByEmployeeId(@PathVariable(value = "employee_id") Integer employeeId) {
		return ResponseEntity.ok(
				this.employeeService.findById(employeeId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
					.getExpenses()
		);
	}
	
	@PostMapping("/{employee_id}/expenses")
	public ResponseEntity<Expense> addOneExpense(@PathVariable(value = "employee_id") Integer employeeId, @RequestBody Expense expense) {
		Employee employee = employeeService.findById(employeeId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.status(201).body(this.expenseService.addOneExpense(employee, expense));
	}
	
	@DeleteMapping("/{employee_id}/expenses/{expense_id}")
	public ResponseEntity<Void> deleteOneExpense(
			@PathVariable(value = "employee_id") Integer employeeId,
			@PathVariable(value = "expense_id") Integer expenseId
	) {
		Employee employee = this.employeeService.findById(employeeId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Expense expense = this.expenseService.findById(expenseId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		this.expenseService.deleteOne(employee, expense);
		return ResponseEntity.noContent().build();
	}

}
