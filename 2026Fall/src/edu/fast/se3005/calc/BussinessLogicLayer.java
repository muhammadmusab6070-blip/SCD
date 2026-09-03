package edu.fast.se3005.calc;

public class BussinessLogicLayer {
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
    if (totalCredits > 0) {
        double cgpa = totalPoints / totalCredits;
        sb.append("\nCGPA: ").append(String.format("%.2f", cgpa));
    }
}
