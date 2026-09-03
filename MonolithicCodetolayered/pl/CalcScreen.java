package pl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bll.Calc;
import dal.Course;
import dal.DataFetcher;
import dal.IDataFetcher;

public class CalcScreen extends JFrame{
    private JTextField rollInput;
    private JTextArea outputArea;
    private JButton searchButton;
    
    private Calc calc;
    private IDataFetcher dataFetcher = new DataFetcher();
    
    
    public CalcScreen() {
    	setTitle("Student CGPA Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        calc = new Calc(dataFetcher);

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
                    Double cgpa = calc.calculateCGPA(rollNo);
                    if (cgpa == null) {
                        outputArea.setText("No data found for roll number: " + rollNo);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (Course c : calc.getLastFetchedCourses()) {
                        if (c != null) {
                            sb.append(c.code)
                              .append(" (")
                              .append(c.creditHours)
                              .append(" CH): ")
                              .append(c.gpa)
                              .append("\n");
                        }
                    }
                    sb.append("\nCGPA: ").append(String.format("%.2f", cgpa));
                    outputArea.setText(sb.toString());
                }
            }
        });
    }
}
