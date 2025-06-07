package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;

public class MainFrame extends JFrame {
    private DatabaseManager dbManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() throws SQLException {
        dbManager = new DatabaseManager();
        setTitle("Управление соискателями");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Имя"}, 0);
        table = new JTable(tableModel);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");

        addButton.addActionListener(e -> addApplicant());
        deleteButton.addActionListener(e -> deleteApplicant());

        panel.add(addButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void loadData() throws SQLException {
        tableModel.setRowCount(0);
        Connection conn = dbManager.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Applicants");
        while (rs.next()) {
            tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name")});
        }
    }

    private void addApplicant() {
        String name = JOptionPane.showInputDialog("Введите имя:");
        if (name != null && !name.trim().isEmpty()) {
            try {
                Connection conn = dbManager.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Applicants (name) VALUES (?)");
                ps.setString(1, name);
                ps.executeUpdate();
                loadData();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void deleteApplicant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            try {
                Connection conn = dbManager.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Applicants WHERE id = ?");


                ps.setInt(1, id);
                ps.executeUpdate();
                loadData();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}

