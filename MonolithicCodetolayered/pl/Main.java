package pl; 

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalcScreen().setVisible(true);
        });
    }
}