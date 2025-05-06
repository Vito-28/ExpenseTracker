package com.tracker.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tracker.model.Category;
import com.tracker.model.Expense;

public class ViewExpenseTracker {
	
	private Scanner scanner = new Scanner(System.in);
	
	public Expense insertAdd(String input) {
		Expense expense = null;
		
		String regex = "^add --description \"([^\"]+)\" --amount (\\d+(?:\\.\\d+)?) --category (Fixed|Variable|Savings)$";
		
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
		
        if(matcher.find()) {
        	expense = new Expense();
        	String description = matcher.group(1);
            Double amount = Double.parseDouble(matcher.group(2));
            String category = matcher.group(3);
            
            expense.setDescription(description);
            expense.setAmount(amount);
            expense.setDate(LocalDate.now());
            
            switch(category) {
            case "Fixed":
            	expense.setCategory(Category.FIXED);
            	break;
            case "Variable":
            	expense.setCategory(Category.VARIABLE);
            	break;
            case "Savings":
            	expense.setCategory(Category.SAVINGS);
            	break;
            }
            
        } else {
        	System.out.println("input non valido!");
        }
        
        return expense;

	}
	
	public Expense insertUpdate(String input) {
	    Expense expense = null;

	    String regex = "update --id (\\d+) --description \"([^\"]+)\" --amount (\\d+(?:\\.\\d+)?) --category (Fixed|Variable|Savings)$";

	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(input);

	    if (matcher.find()) {
	        expense = new Expense();
	        
	        Long id = Long.parseLong(matcher.group(1));
	        String description = matcher.group(2);
	        Double amount = Double.parseDouble(matcher.group(3));
	        String category = matcher.group(4);
	        
	        switch(category) {
            case "Fixed":
            	expense.setCategory(Category.FIXED);
            	break;
            case "Variable":
            	expense.setCategory(Category.VARIABLE);
            	break;
            case "Savings":
            	expense.setCategory(Category.SAVINGS);
            	break;
            }
	        
	        expense.setId(id);
	        expense.setDescription(description);
	        expense.setAmount(amount);
	        expense.setDate(LocalDate.now()); 
	    } else {
	        System.out.println("input non valido!");
	    }

	    return expense;
	}
    
	public Month insertMonth(String inputMonth) {
		Month month = null;
		
		switch(inputMonth) {
		case "9":
			month = Month.SEPTEMBER;
			break;
		case "10":
			month = Month.OCTOBER;
			break;
		case "11":
			month = Month.NOVEMBER;
			break;
		case "12":
			month = Month.DECEMBER;
			break;
		case "1":
			month = Month.JANUARY;
			break;
		case "2":
			month = Month.FEBRUARY;
			break;
		case "3":
			month = Month.MARCH;
			break;
		case "4":
			month = Month.APRIL;
			break;
		case "5":
			month = Month.MAY;
			break;
		case "6":
			month = Month.JUNE;
			break;
		case "7":
			month = Month.JULY;
			break;
		case "8":
			month = Month.AUGUST;
			break;
		default:
			System.out.println("Error Month!!!");
		}
		
		return month;
	}
	
	public Double insertBudget(String inputBudget) {
		return Double.parseDouble(inputBudget);
	}
	
	public void printBudgetForMonth(Map<Month, Double> budgets) {
		System.out.println("# Month    Budget");
		for (Map.Entry<Month, Double> entry : budgets.entrySet()) {
			Month key = entry.getKey();
			Double val = entry.getValue();
			System.out.println("# "+key + "   " + val);
		}
	}
	
	public void printList(List<Expense> expense) {
		
		if(expense == null) {
			System.out.println("Expense Doesn't Exist");
			return;
		}
		
		System.out.println("# ID  Date       Description  Amount  Category");
		
		for(Expense exp : expense) {
			System.out.println("# " + exp.getId() + "   " + exp.getDate() + "  " + exp.getDescription() + "       $" + exp.getAmount() + "    " + exp.getCategory());
		}
	
	}
	
	public void printSummary(Double sum) {
		System.out.println("# Total expenses: $" + sum);
	}
	
	public Long insertId(String idStr) {
		return Long.parseLong(idStr);
	}
	
	public Double insertAmount(String amountStr) {
		return Double.parseDouble(amountStr);
	}
	
	public void print(String s) {
		System.out.println(s);
	}
	
	public String input() {
		System.out.print("$ expense-tracker ");
		String input = scanner.nextLine();
		
		return input;
	}
	
}
