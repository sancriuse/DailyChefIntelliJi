package ui;

import javax.swing.*;

// Runs the Recipe Catalog application
public class Main {
    public static void main(String[] args) {
        JFrame frame = new DailyChefApp("DailyChef Recipe Catalog");
        frame.setVisible(true);
        frame.setSize(750, 600);
        frame.setResizable(false);
    }
}