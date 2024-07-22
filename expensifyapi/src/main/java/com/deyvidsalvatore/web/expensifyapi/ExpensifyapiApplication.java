package com.deyvidsalvatore.web.expensifyapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			List<Employee> employees = List.of(
					new Employee("clecio1", passwordEncoder.encode("clecio@pass"),
							List.of(Expense.builder().merchant("merchant_1").description("description_1")
									.purchaseDate(LocalDate.now()).amount(123.45).build())),
					new Employee("davy2", passwordEncoder.encode("davy@pass")), new Employee("felca3", passwordEncoder.encode("felca@pass")));

			Reviewer reviewer1 = new Reviewer("revpucci1", passwordEncoder.encode("pucci@pass"));
			Reviewer reviewer2 = new Reviewer("revdio2", passwordEncoder.encode("dio@pass"));

			employees.forEach(employeeRepository::save);
			reviewerRepository.save(reviewer1);
			reviewerRepository.save(reviewer2);
		};
	}
}
