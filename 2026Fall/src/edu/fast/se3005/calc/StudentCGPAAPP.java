// file name StudentCGPAApp.java
package edu.fast.se3005.calc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentCGPAAPP extends JFrame {
    private JTextField rollInput;
    private JTextArea outputArea;
    private JButton searchButton;

    public StudentCGPAAPP() {
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
        File file = new File("data/" + rollNo + ".txt");
        if (!file.exists()) {
            outputArea.setText("No data found for roll number: " + rollNo);
            return;
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
            if (totalCredits > 0) {
                double cgpa = totalPoints / totalCredits;
                sb.append("\nCGPA: ").append(String.format("%.2f", cgpa));
            }
            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error reading data for roll number: " + rollNo);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentCGPAAPP().setVisible(true);
        });
    }
}