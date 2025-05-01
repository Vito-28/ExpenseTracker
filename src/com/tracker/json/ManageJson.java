package com.tracker.json;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tracker.model.Expense;

public class ManageJson {
	
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
	    
	    jsonArray.put(jsonObject);

	    try {
	        Files.write(path, jsonArray.toString(4).getBytes());
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
	        jsonArray.put(jsonObject);
	    }

	    try {
	        Files.write(path, jsonArray.toString(4).getBytes());
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return false;   
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
	            
	            exp.setId(id);
	            exp.setDescription(description);
	            exp.setAmount(amount);
	            exp.setDate(date);
	            
	            expense.add(exp);
	        }
	        
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		return expense;
	}
	
}
