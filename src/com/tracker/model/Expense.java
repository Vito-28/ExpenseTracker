package com.tracker.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Expense {
	private Long id;
	private String description;
	private Double amount;
	private LocalDate date;
	private Category category;
	
	public Expense() {
		super();
	}

	public Expense(Long id, String description, Double amount, LocalDate date, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", description=" + description + ", amount=" + amount + ", date=" + date
				+ ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, category, date, description, id);
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
		return Objects.equals(amount, other.amount) && category == other.category && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}
	
}
