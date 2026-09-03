package bll;

import dal.Course;
import dal.IDataFetcher;

public class Calc {
	private IDataFetcher dal;
	private Course[] lastFetched;
	
	public Calc(IDataFetcher dal) {
		this.dal = dal;
	}
	public Double calculateCGPA(String rollNo) {
		Course[] table  = dal.FetchStudentData(rollNo);
		lastFetched = table;
		
		if(table == null) {
			return null;
		}
		
		double totalPoints = 0.0;
		int totalCredits = 0;
		for (Course c : table) {
			if(c!=null) {
				totalPoints += c.creditHours * c.gpa;
				totalCredits += c.creditHours;
			}
		}
		
		if(totalCredits == 0) {
			return null;
		}
		
		return (totalPoints/totalCredits);
	}
	
	public Course[] getLastFetchedCourses() {
		return lastFetched;
	}
}
