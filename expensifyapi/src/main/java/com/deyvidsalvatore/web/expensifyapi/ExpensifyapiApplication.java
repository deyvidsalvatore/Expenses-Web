package com.deyvidsalvatore.web.expensifyapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.deyvidsalvatore.web.expensifyapi.models.Employee;
import com.deyvidsalvatore.web.expensifyapi.models.Expense;
import com.deyvidsalvatore.web.expensifyapi.models.Reviewer;
import com.deyvidsalvatore.web.expensifyapi.repositories.EmployeeRepository;
import com.deyvidsalvatore.web.expensifyapi.repositories.ReviewerRepository;

@SpringBootApplication
public class ExpensifyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensifyapiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository) {
		return args -> {
			List<Employee> employees = List.of(
					new Employee("clecio1", "clecio@pass",
							List.of(Expense.builder().merchant("merchant_1").description("description_1")
									.purchaseDate(LocalDate.now()).amount(123.45).build())),
					new Employee("davy2", "davy@pass"), new Employee("felca3", "felca@pass"));

			Reviewer reviewer1 = new Reviewer("revpucci1", "pucci@pass");
			Reviewer reviewer2 = new Reviewer("revdio2", "dio@pass");

			employees.forEach(employeeRepository::save);
			reviewerRepository.save(reviewer1);
			reviewerRepository.save(reviewer2);
		};
	}
}
