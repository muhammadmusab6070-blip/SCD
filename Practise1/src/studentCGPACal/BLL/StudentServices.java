package studentCGPACal.BLL;

import java.util.ArrayList;
import studentCGPACal.DAL.StudentDataAccess;

public class StudentServices {

    private StudentDataAccess dataAccess;

    public StudentServices() {
        dataAccess = new StudentDataAccess();
    }

    public double CalculateCGPA(int totalCredits, double totalPoints) {
        double cgpa = totalPoints / totalCredits;
        return cgpa;
    }

    public ArrayList<String[]> getStudentData(String rollNo) {

        ArrayList<String[]> data = dataAccess.readStudentData(rollNo);

        return data;
    }

    public double getStudentCGPA(String rollNo) {

        ArrayList<String[]> data = dataAccess.readStudentData(rollNo);

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (String[] parts : data) {

            int creditHours = Integer.parseInt(parts[1].trim());
            double gpa = Double.parseDouble(parts[2].trim());

            totalPoints += creditHours * gpa;
            totalCredits += creditHours;
        }

        if (totalCredits > 0) {
            return CalculateCGPA(totalCredits, totalPoints);
        }

        return 0.0;
    }
}