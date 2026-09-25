package studentCGPACal.DAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class StudentDataAccess {
	public ArrayList<String[]> readStudentData(String rollNo) {
		
		ArrayList<String []> data = new ArrayList<String []>();	
		
		File file = new File("src/" + rollNo + ".txt");
		
		if (!file.exists()) {
	        return data;
	    }
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				
				if (parts.length == 3) {
					//String course = parts[0].trim();
	                //int creditHours = Integer.parseInt(parts[1].trim());
	                //double gpa = Double.parseDouble(parts[2].trim());
	                
					// we are gonna replace parse code with this 
					
					data.add(parts);
				}
				
			}
		}
		catch (Exception ex) {

        }
		
		return data;
	}
	

}
