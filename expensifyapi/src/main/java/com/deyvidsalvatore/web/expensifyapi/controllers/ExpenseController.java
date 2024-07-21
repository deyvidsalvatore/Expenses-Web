package com.deyvidsalvatore.web.expensifyapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deyvidsalvatore.web.expensifyapi.models.Expense;
import com.deyvidsalvatore.web.expensifyapi.models.Status;
import com.deyvidsalvatore.web.expensifyapi.services.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	
	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	

	/* Status from Employee's Expenses */
	@PutMapping("/{expense_id}/status")
	public ResponseEntity<Void> updateExpenseStatus(
			@PathVariable(value = "expense_id") Integer expenseId,
			@RequestBody Status updatedStatus
	) {
		Expense expense = this.expenseService.findById(expenseId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		expense.getStatus().changeTo(updatedStatus);
		this.expenseService.save(expense);
		return ResponseEntity.noContent().build();
	}

}
