package com.tracker.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tracker.model.Category;
import com.tracker.model.Expense;

public class ManageJson {
	
	public boolean writeObjectJson(Expense expense, List<Expense> expenses) {
		
		for(int i = 0; i < expenses.size(); i++) {
			if(expenses.get(i).getId().equals(expense.getId())) {
				expenses.set(i, expense);
				return writeFileJson(expenses);
			}
		}
		
	    return false;
	}
	
	public boolean writeFileJson(Expense expense) {
	    Path path = Paths.get("expense.json");
	    JSONArray jsonArray = new JSONArray();

	    try {
	        if (Files.exists(path) && Files.size(path) > 0) {
	            String content = new String(Files.readAllBytes(path));
	            jsonArray = new JSONArray(content);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("id", expense.getId());
	    jsonObject.put("description", expense.getDescription());
	    jsonObject.put("amount", expense.getAmount());
	    jsonObject.put("date", expense.getDate());
	    jsonObject.put("category", expense.getCategory());
	    
	    jsonArray.put(jsonObject);

	    try {
	        Files.write(path, jsonArray.toString(5).getBytes());
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean writeFileJson(List<Expense> expenses) {
	    Path path = Paths.get("expense.json");
	    JSONArray jsonArray = new JSONArray();
	    
	    for (Expense expense : expenses) {
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("id", expense.getId());
	        jsonObject.put("description", expense.getDescription());
	        jsonObject.put("amount", expense.getAmount());
	        jsonObject.put("date", expense.getDate().toString());
	        jsonObject.put("category", expense.getCategory().toString());
	        jsonArray.put(jsonObject);
	    }

	    try {
	        Files.write(path, jsonArray.toString(5).getBytes());
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return false;   
	}

	public void writeBudgetsFileJson(Map<Month, Double> budgets) {
	    Path path = Paths.get("budgets.json");
	    JSONObject jsonObject = new JSONObject();

	    // Se il file esiste e contiene dati, carica il contenuto esistente
	    try {
	        if (Files.exists(path) && Files.size(path) > 0) {
	            String content = new String(Files.readAllBytes(path));
	            jsonObject = new JSONObject(content);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Aggiorna solo i mesi presenti nella Map passata
	    for (Map.Entry<Month, Double> entry : budgets.entrySet()) {
	        Month month = entry.getKey();
	        Double value = entry.getValue();
	        jsonObject.put(month.name().toLowerCase(), value);
	    }

	    // Scrive il JSONObject nel file, sovrascrivendo il file esistente
	    try {
	        Files.write(path, jsonObject.toString(4).getBytes());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public Map<Month, Double> getBudgetsFromMonthFileJson() {
	    Map<Month, Double> budgets = new HashMap<Month, Double>();
	    Path path = Paths.get("budgets.json");

	    try {
	        if (!Files.exists(path)) {
	            // Crea il file con un oggetto JSON vuoto
	            Files.write(path, "{}".getBytes());
	        }

	        String content = new String(Files.readAllBytes(path));

	        if (!content.trim().isEmpty()) {
	            JSONObject jsonObject = new JSONObject(content);

	            for (String key : jsonObject.keySet()) {
	                try {
	                    Month month = Month.valueOf(key.toUpperCase());
	                    Double value = jsonObject.getDouble(key);
	                    budgets.put(month, value);
	                } catch (IllegalArgumentException e) {
	                    System.err.println("Chiave non valida (non è un mese): " + key);
	                }
	            }
	        }
	    } catch (IOException | JSONException e) {
	        e.printStackTrace();
	    }

	    return budgets;
	}
	
	public List<Expense> getExpenseFromFileJSON() {
		List<Expense> expense = new ArrayList<Expense>();
		
		try {
			String filePath = "expense.json";
			Path path = Paths.get(filePath);
	        String jsonString;
	        
	        File file = new File(filePath);
	        
	        if (file.exists()) {
	            jsonString = new String(Files.readAllBytes(path));
	        } else {
	        	Files.write(path, "[]".getBytes());
	            jsonString = "[]";
	        }
	        
	        JSONArray jsonArray = new JSONArray(jsonString);
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
	        for(int i = 0; i < jsonArray.length(); i++) {
	        	Expense exp = new Expense();
	        	JSONObject jsonObject = jsonArray.getJSONObject(i);
	            Long id = jsonObject.getLong("id");
	            String description = jsonObject.getString("description");
	            Double amount = jsonObject.getDouble("amount");
	            String dateStr = jsonObject.getString("date");
	            
	            LocalDate date = LocalDate.parse(dateStr, formatter);
	            
	            Category category = null;
	            switch(jsonObject.getString("category")) {
	            case "FIXED":
	            	category = Category.FIXED;
	            	break;
	            case "VARIABLE":
	            	category = Category.VARIABLE;
	            	break;
	            case "SAVINGS":
	            	category = Category.SAVINGS;
	            	break;
	            }
	            
	            exp.setId(id);
	            exp.setDescription(description);
	            exp.setAmount(amount);
	            exp.setDate(date);
	            exp.setCategory(category);
	            
	            expense.add(exp);
	        }
	        
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		return expense;
	}
	
}
