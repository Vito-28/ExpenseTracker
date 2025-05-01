package com.tracker.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tracker.model.Expense;

public class ViewExpenseTracker {
	
	private Scanner scanner = new Scanner(System.in);
	
	public Expense insert(String input) {
		Expense expense = null;
		
		String regex = "add --description \"([^\"]+)\" --amount (\\d+(?:\\.\\d+)?)";
		
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
		
        if(matcher.find()) {
        	expense = new Expense();
        	String description = matcher.group(1);
            Double amount = Double.parseDouble(matcher.group(2));
            
            expense.setDescription(description);
            expense.setAmount(amount);
            expense.setDate(LocalDate.now());
            
        } else {
        	System.out.println("input non valido!");
        }
        
        return expense;

	}
	
	public void printList(List<Expense> expense) {
		
		System.out.println("# ID  Date       Description  Amount");
		
		for(Expense exp : expense) {
			System.out.println("# " + exp.getId() + "   " + exp.getDate() + "  " + exp.getDescription() + "       $" + exp.getAmount());
		}
	
	}
	
	public void printSummary(Double sum) {
		System.out.println("# Total expenses: $" + sum);
	}
	
	public Long insertId(String idStr) {
		return Long.parseLong(idStr);
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
