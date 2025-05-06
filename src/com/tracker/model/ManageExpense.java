
package com.tracker.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tracker.json.ManageJson;

public class ManageExpense {
	
	private ManageJson manageJson = new ManageJson();
	private Long id = getListExpense().size() != 0 ? getListExpense().getLast().getId()+1 : 1 ;
	private boolean isExit = false;
	
	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public void addExpense(Expense expense) {
		
		if(!checkBudget(expense)) {
			System.out.println("Exceeded Monthly Budget");
			return;
		}
		
		if(expense != null && !expense.equals(selectExpenseForId(id))) {
			expense.setId(id);
			manageJson.writeFileJson(expense);
			System.out.println("# Expense added successfully (ID:"+ id +")");
			id++;
		}
	}
	
	public Expense selectExpenseForId(Long id) {
		for(Expense exp : getListExpense()) {
			if(exp.getId() == id) {
				return exp;
			}
		}
		return null;
	}
	
	public Double summary() {
		Double sum = 0.0;
		
		for(Expense exp : getListExpense()) {
			sum += exp.getAmount();
		}
		
		return sum;
	}
	
	public void delete(Long id) {
		Expense exp = selectExpenseForId(id);
		if(exp != null) {
			List<Expense> expense = getListExpense();
			expense.remove(exp);
			System.out.println("# Expense deleted successfully");
			manageJson.writeFileJson(expense);
		} else {
			System.out.println("Expense Doesn't Exist");
		}
	}
	
	public Double summaryForMonth(Month month) {
        Double sum = 0.0;
		
		for(Expense exp : getListExpense()) {
			
			if(exp.getDate().getMonth().equals(month)) {
				sum += exp.getAmount();
			}
			
		}
		
		return sum;
	}
	
	public void update(Expense tmp) {
		Expense exp = selectExpenseForId(tmp.getId());
		exp.setDescription(tmp.getDescription());
		exp.setAmount(tmp.getAmount());
		exp.setDate(tmp.getDate());
		exp.setCategory(tmp.getCategory());
		if(manageJson.writeObjectJson(exp, getListExpense())) {
			System.out.println("# Expense update successfully");
		} else {
			System.out.println("# Expense Doesn't Exist");
		}
		
	}
	
	public boolean checkBudget(Expense expense) {
		
		Map<Month, Double> budgets = getMapBudgets();
		Month month = expense.getDate().getMonth();
		Double budget = budgets.get(month);
		Double tmp = summaryForMonth(month) + expense.getAmount();
		System.out.println(month + " " + budget + " " + tmp);
		
		return (tmp <= budget);
		
	}
	
	public List<Expense> getListExpenseForCategory(Category category) {
		
		List<Expense> expenses = new ArrayList<Expense>();
		
		for(Expense expense : getListExpense()) {
			if(expense.getCategory().equals(category)) {
				expenses.add(expense);
			}
		}
		
		return (expenses.size() == 0 ? null : expenses);
		
	}

	public List<Expense> getListExpense() {
		return manageJson.getExpenseFromFileJSON();
	}
	
	public void setBudgetForMonth(Month month, Double budget) {
		Map<Month, Double> budgets = getMapBudgets();
		
		budgets.put(month, budget);
		
		manageJson.writeBudgetsFileJson(budgets);
	}
	
	public Map<Month, Double> getMapBudgets() {
		Map<Month, Double> map = manageJson.getBudgetsFromMonthFileJson();
		
		if(map.size() == 0) {
			map.put(Month.SEPTEMBER, 0.0);
			map.put(Month.OCTOBER, 0.0);
			map.put(Month.NOVEMBER, 0.0);
			map.put(Month.DECEMBER, 0.0);
			map.put(Month.JANUARY, 0.0);
			map.put(Month.FEBRUARY, 0.0);
			map.put(Month.MARCH, 0.0);
			map.put(Month.APRIL, 0.0);
			map.put(Month.MAY, 0.0);
			map.put(Month.JUNE, 0.0);
			map.put(Month.JULY, 0.0);
			map.put(Month.AUGUST, 0.0);
		}
		
		return map;
	}
}
