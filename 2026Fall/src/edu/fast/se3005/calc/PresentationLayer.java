package edu.fast.se3005.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationLayer {
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
