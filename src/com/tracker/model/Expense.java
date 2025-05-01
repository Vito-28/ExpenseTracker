package com.tracker.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Expense {
	private Long id;
	private String description;
	private Double amount;
	private LocalDate date;
	
	public Expense() {
		super();
	}

	public Expense(Long id, String description, Double amount, LocalDate date) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", description=" + description + ", amount=" + amount + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, description, id);
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
		return Objects.equals(amount, other.amount) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}
	
}
