//package gui;
//
//import db.DBConnection;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.sql.*;
//
//public class SearchItemFrame extends JFrame {
//
//    private JTextField searchField;
//    private JTable resultTable;
//
//    public SearchItemFrame() {
//        setTitle("Search Items");
//        setSize(500, 350);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        JPanel topPanel = new JPanel();
//        topPanel.add(new JLabel("Enter Item ID or Name:"));
//        searchField = new JTextField(20);
//        topPanel.add(searchField);
//
//        JButton searchBtn = new JButton("Search");
//        topPanel.add(searchBtn);
//        add(topPanel, BorderLayout.NORTH);
//
//        resultTable = new JTable(new DefaultTableModel(new String[]{"Item ID", "Name", "Quantity", "Price"}, 0));
//        add(new JScrollPane(resultTable), BorderLayout.CENTER);
//
//        searchBtn.addActionListener(e -> searchItems());
//
//        setVisible(true);
//    }
//
//    private void searchItems() {
//        String keyword = searchField.getText().trim();
//        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
//        model.setRowCount(0);
//
//        if (keyword.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter a search keyword.");
//            return;
//        }
//
//        String sql = "SELECT * FROM items WHERE name LIKE ? OR item_id = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, "%" + keyword + "%");
//            try {
//                ps.setInt(2, Integer.parseInt(keyword));
//            } catch (NumberFormatException e) {
//                ps.setInt(2, -1);
//            }
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Object[] row = {
//                        rs.getInt("item_id"),
//                        rs.getString("name"),
//                        rs.getInt("quantity"),
//                        rs.getDouble("price")
//                };
//                model.addRow(row);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Search failed.");
//        }
//    }
//}


//package gui;
//
//import db.DBConnection;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.sql.*;
//
//public class SearchItemFrame extends JFrame {
//
//    private JTextField searchField;
//    private JTable resultTable;
//
//    public SearchItemFrame() {
//        setTitle("Search Items");
//        setSize(600, 450);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        // Main Panel
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        add(mainPanel);
//
//        // Header
//        JLabel header = new JLabel("Search Items", SwingConstants.CENTER);
//        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        header.setOpaque(true);
//        header.setBackground(new Color(0, 102, 204));
//        header.setForeground(Color.WHITE);
//        header.setPreferredSize(new Dimension(600, 60));
//        mainPanel.add(header, BorderLayout.NORTH);
//
//        // Search Panel
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        searchPanel.setBackground(Color.WHITE);
//
//        JLabel searchLabel = new JLabel("Enter Item ID or Name:");
//        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        searchPanel.add(searchLabel);
//
//        searchField = new JTextField(20);
//        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        searchPanel.add(searchField);
//
//        JButton searchBtn = new JButton("Search");
//        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        searchPanel.add(searchBtn);
//
//        mainPanel.add(searchPanel, BorderLayout.CENTER);
//
//        // Table Panel
//        resultTable = new JTable(new DefaultTableModel(new String[]{"Item ID", "Name", "Quantity", "Price"}, 0));
//        JScrollPane scrollPane = new JScrollPane(resultTable);
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results"));
//
//        JPanel tablePanel = new JPanel(new BorderLayout());
//        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        tablePanel.add(scrollPane, BorderLayout.CENTER);
//
//        mainPanel.add(tablePanel, BorderLayout.SOUTH);
//
//        // Action Listener
//        searchBtn.addActionListener(e -> searchItems());
//
//        setVisible(true);
//    }
//
//    private void searchItems() {
//        String keyword = searchField.getText().trim();
//        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
//        model.setRowCount(0);
//
//        if (keyword.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter a search keyword.");
//            return;
//        }
//
//        String sql = "SELECT * FROM items WHERE name LIKE ? OR item_id = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, "%" + keyword + "%");
//            try {
//                ps.setInt(2, Integer.parseInt(keyword));
//            } catch (NumberFormatException e) {
//                ps.setInt(2, -1);
//            }
//
//            ResultSet rs = ps.executeQuery();
//            boolean found = false;
//
//            while (rs.next()) {
//                Object[] row = {
//                        rs.getInt("item_id"),
//                        rs.getString("name"),
//                        rs.getInt("quantity"),
//                        rs.getDouble("price")
//                };
//                model.addRow(row);
//                found = true;
//            }
//
//            if (!found) {
//                JOptionPane.showMessageDialog(this, "No items found.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Search failed.");
//        }
//    }
//}


package gui;

import db.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SearchItemFrame extends JFrame {

    private JTextField searchField;
    private JTable resultTable;

    public SearchItemFrame() {
        setTitle("Search Items");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Search Items", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(600, 50));
        add(header, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Enter Item ID or Name:");
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.SOUTH); // Place below the table

        // Table
        resultTable = new JTable(new DefaultTableModel(new String[]{"Item ID", "Name", "Quantity", "Price"}, 0));
        JScrollPane tableScroll = new JScrollPane(resultTable);
        add(tableScroll, BorderLayout.CENTER);

        // Action
        searchBtn.addActionListener(e -> searchItems());

        setVisible(true);
    }

    private void searchItems() {
        String keyword = searchField.getText().trim();
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        model.setRowCount(0);

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search keyword.");
            return;
        }

        String sql = "SELECT * FROM items WHERE name LIKE ? OR item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try {
                ps.setInt(2, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                ps.setInt(2, -1);
            }

            ResultSet rs = ps.executeQuery();
            boolean found = false;

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                };
                model.addRow(row);
                found = true;
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No items found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Search failed.");
        }
    }
}

