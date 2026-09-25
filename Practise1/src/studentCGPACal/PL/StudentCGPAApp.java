package studentCGPACal.PL;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import studentCGPACal.BLL.StudentServices;

public class StudentCGPAApp extends JFrame {
	private JTextField rollInput;
	private JTextArea outputArea;
	private JButton searchButton;
	// Creating BLL Object and initialize it inside constructor 
	private StudentServices services;
	
	public StudentCGPAApp() {
		
		services = new StudentServices();
		
	    setTitle("Student CGPA Calculator");
	    setSize(450, 350);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    JPanel topPanel = new JPanel(new FlowLayout());
	    topPanel.add(new JLabel("Roll Number:"));
	    rollInput = new JTextField(15);
	    topPanel.add(rollInput);
	    searchButton = new JButton("Search");
	    topPanel.add(searchButton);

	    outputArea = new JTextArea();
	    outputArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(outputArea);

	    add(topPanel, BorderLayout.NORTH);
	    add(scrollPane, BorderLayout.CENTER);

	    searchButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String rollNo = rollInput.getText().trim();
	            if (!rollNo.isEmpty()) {
	                displayStudentData(rollNo);
	            }
	        }
	    });
	}
	
	private void displayStudentData(String rollNo) {

        ArrayList<String[]> data = services.getStudentData(rollNo);

        StringBuilder sb = new StringBuilder();

        for (String[] parts : data) {

            String course = parts[0].trim();
            int creditHours = Integer.parseInt(parts[1].trim());
            double gpa = Double.parseDouble(parts[2].trim());

            sb.append(course)
              .append(" (")
              .append(creditHours)
              .append(" CH): ")
              .append(gpa)
              .append("\n");
        }

        if (data.size() > 0) {

            double cgpa = services.getStudentCGPA(rollNo);

            sb.append("\nCGPA: ")
              .append(String.format("%.2f", cgpa));
        }
        else {

            sb.append("No data found for roll number: ")
              .append(rollNo);
        }

        outputArea.setText(sb.toString());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentCGPAApp().setVisible(true);
        });
    }
}
