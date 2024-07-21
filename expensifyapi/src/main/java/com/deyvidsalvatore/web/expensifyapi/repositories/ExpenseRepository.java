package com.deyvidsalvatore.web.expensifyapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.deyvidsalvatore.web.expensifyapi.models.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

}
