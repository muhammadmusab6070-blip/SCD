package dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataFetcher implements IDataFetcher {
	
	@Override
	public Course[] FetchStudentData(String rollNo) {
		File file = new File("data/" + rollNo + ".txt");
        if (!file.exists()) {
            return null;
        }
        Course[] table = new Course[10];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String course = parts[0].trim();
                    int creditHours = Integer.parseInt(parts[1].trim());
                    double gpa = Double.parseDouble(parts[2].trim());
                    table[i] = new Course(course, creditHours, gpa);
                    i++;
                }
            }
        } catch (Exception ex) {
        	return null;
        }
		return table;
	}
}
