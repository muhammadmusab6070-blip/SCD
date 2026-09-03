package dal;

public class Course {
	public String code;
	public int creditHours;
	public double gpa;
	
	public Course(String code, int crdHrs, double gpa) {
		this.code = code;
		this.creditHours = crdHrs;
		this.gpa = gpa;
	}
}
