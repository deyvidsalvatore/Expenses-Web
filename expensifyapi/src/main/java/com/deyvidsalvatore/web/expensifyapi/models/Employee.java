package com.deyvidsalvatore.web.expensifyapi.models;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends ExpensifyUser {
	
	@Serial
	private static final long serialVersionUID = 1L;

	protected Employee() {}
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	private List<Expense> expenses;
	
	public Employee(String username, String password) {
		this(username, password, new ArrayList<>());
	}
	
	public Employee(String username, String password, List<Expense> expenses) {
		super(username, password, Role.ROLE_EMPLOYEE);
		this.expenses = expenses;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

}
