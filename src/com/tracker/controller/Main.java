package com.tracker.controller;

import java.time.Month;

import com.tracker.model.ManageExpense;
import com.tracker.view.ViewExpenseTracker;

public class Main {

	public static void main(String[] args) {
		
		ManageExpense m = new ManageExpense();
		ViewExpenseTracker v = new ViewExpenseTracker();
		
		String input;
	    do {
	    	input = v.input();
	    	String tmp = "";
	    	String operation = "";
	    	String idStr = "";
	    	String description = "";
	    	String amountStr = "";
	    	String month = "";
	    	if(input.contains("add")) {
	    		tmp = input;
	    		operation = input.substring(0, 3);
	    		input = operation;
	    	} else if(input.contains("delete --id")) {
	    		String[] inputSplit = input.split(" ");
	    		input = inputSplit[0];
	    		idStr = inputSplit[2]; 
	    	} else if(input.contains("summary --month")) {
	    		String[] inputSplit = input.split(" ");
	    		input = inputSplit[0]+ " " +inputSplit[1];
	    		month = inputSplit[2];
	    	} else if(input.contains("update")) {
	    		String[] inputSplit = input.split(" ");
	    		operation = input;
	    		input = inputSplit[0];
	    	}
	    	
	    	switch(input) {
	    	case "add":
	    		m.addExpense(v.insertAdd(tmp));
	    		break;
	    	case "list":
	    		v.printList(m.getListExpense());
	    		break;
	    	case "summary":
	    		v.printSummary(m.summary());
	    		break;
	    	case "delete":
	    		m.delete(v.insertId(idStr));
	    		break;
	    	case "update":
	    		m.update(v.insertUpdate(operation));
	    		break;
	    	case "summary --month":
	    		v.print("# Total expenses for "+
	    	            (Month.of(Integer.parseInt(month))).toString().substring(0, 1).toUpperCase() +
	    	            (Month.of(Integer.parseInt(month))).toString().substring(1).toLowerCase() +": " + 
	    	             m.summaryForMonth(Month.of(Integer.parseInt(month))));
	    		break;
	    	case "exit":
	    		m.setExit(true);
	    		break;
	    	default:
	    		System.out.println("Comando Errato!!");
	    	}
	    } while(!m.isExit());
		
	}

}
