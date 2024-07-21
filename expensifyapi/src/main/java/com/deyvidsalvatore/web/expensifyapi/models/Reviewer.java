package com.deyvidsalvatore.web.expensifyapi.models;

import java.io.Serial;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {

	@Serial
	private static final long serialVersionUID = 1L;

	protected Reviewer() {}
	
	public Reviewer(String username, String password) {
		super(username, password, Role.REVIEWER);
	}

}
