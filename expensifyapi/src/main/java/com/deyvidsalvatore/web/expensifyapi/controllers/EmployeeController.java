package com.deyvidsalvatore.web.expensifyapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.models.Expense;
import com.deyvidsalvatore.web.expensifyapi.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
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

}
