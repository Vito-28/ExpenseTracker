package com.tracker.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.tracker.model.Expense;

public class ManageCSV {
	
	public void exportInCSV(List<Expense> expenses) {
		
		try(FileWriter writer = new FileWriter("expenses.csv")) {
			writer.append("ID,Description,Amount,Category,Data\n");
			
			for(Expense expense : expenses) {
				writer.append(expense.getId() + ",");
				writer.append(expense.getDescription() + ",");
				writer.append(expense.getAmount() + "$,");
				writer.append(expense.getCategory() + ",");
				writer.append(expense.getDate() + "\n");
			}
			System.out.println("Export Expense In File CSV");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
