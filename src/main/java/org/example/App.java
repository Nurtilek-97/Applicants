package org.example;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame().setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    }

