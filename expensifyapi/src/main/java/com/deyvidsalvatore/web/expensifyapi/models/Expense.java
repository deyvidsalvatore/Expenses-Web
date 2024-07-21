package com.deyvidsalvatore.web.expensifyapi.models;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String merchant;
	private String description;
	@JsonProperty("purchase_date")
	private LocalDate purchaseDate;
	private double amount;
	@OneToOne(cascade = CascadeType.ALL)
	private Status status;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMerchant() {
		return merchant;
	}
	
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, description, id, merchant, purchaseDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(merchant, other.merchant) && Objects.equals(purchaseDate, other.purchaseDate)
				&& Objects.equals(status, other.status);
	}
	
	/* Builder */
	private Expense() {}
	
	private Expense(ExpenseBuilder builder) {
		this.merchant = builder.merchant;
		this.description = builder.description;
		this.purchaseDate = builder.purchaseDate;
		this.amount = builder.amount;
		this.status = Status.builder().state(Status.State.IN_REVIEW).build();
	}
	
	public static class ExpenseBuilder {
        private String merchant;
        private String description;
        private LocalDate purchaseDate;
        private double amount;

        public ExpenseBuilder() {
        }

        public ExpenseBuilder merchant(String merchant) {
            this.merchant = merchant;
            return this;
        }

        public ExpenseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder purchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public ExpenseBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }
        
        public Expense build() {
            return new Expense(this);
        }
    }
	
	public static ExpenseBuilder builder() {
		return new ExpenseBuilder();
	}
	
}
