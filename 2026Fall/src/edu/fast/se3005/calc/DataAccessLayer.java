package edu.fast.se3005.calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataAccessLayer {
	File file = new File("data/" + rollNo + ".txt") ;
    if (!file.exists()) {
        return ;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        double totalPoints = 0.0;
        int totalCredits = 0;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String course = parts[0].trim();
                int creditHours = Integer.parseInt(parts[1].trim());
                double gpa = Double.parseDouble(parts[2].trim());
                sb.append(course)
                  .append(" (")
                  .append(creditHours)
                  .append(" CH): ")
                  .append(gpa)
                  .append("\n");
                totalPoints += creditHours * gpa;
                totalCredits += creditHours;
            }
        }
      
    } catch (Exception ex) {
        return;
    }
}
