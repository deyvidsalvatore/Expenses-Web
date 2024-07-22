package com.deyvidsalvatore.web.expensifyapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.deyvidsalvatore.web.expensifyapi.models.Reviewer;

public interface ReviewerRepository extends CrudRepository<Reviewer, Integer> {
	Optional<Reviewer> findByUsername(String username);
}
