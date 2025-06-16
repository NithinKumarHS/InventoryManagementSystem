//package gui;
//
//import db.DBConnection;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import java.awt.*;
//import java.sql.*;
//
//public class ViewItemsFrame extends JFrame {
//
//    public ViewItemsFrame() {
//        setTitle("View Inventory Items");
//        setSize(600, 400);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Header
//        JLabel header = new JLabel("Inventory Items", SwingConstants.CENTER);
//        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        header.setOpaque(true);
//        header.setBackground(new Color(0, 102, 204));
//        header.setForeground(Color.WHITE);
//        header.setPreferredSize(new Dimension(600, 60));
//        add(header, BorderLayout.NORTH);
//
//        // Table columns
//        String[] columns = { "Item ID", "Name", "Quantity", "Price" };
//        DefaultTableModel model = new DefaultTableModel(columns, 0);
//        JTable table = new JTable(model);
//        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        table.setRowHeight(24);
//        table.setGridColor(Color.LIGHT_GRAY);
//
//        JTableHeader tableHeader = table.getTableHeader();
//        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        tableHeader.setBackground(new Color(0, 153, 255));
//        tableHeader.setForeground(Color.WHITE);
//
//        // Scroll pane
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Fetch data from DB
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "SELECT * FROM items";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                int id = rs.getInt("item_id");
//                String name = rs.getString("name");
//                int quantity = rs.getInt("quantity");
//                double price = rs.getDouble("price");
//
//                Object[] row = { id, name, quantity, price };
//                model.addRow(row);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Failed to load items.");
//        }
//
//        setVisible(true);
//    }
//}
//


package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class ViewItemsFrame extends JFrame {

    public ViewItemsFrame() {
        setTitle("View Inventory Items");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Inventory Items", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204)); // Blue theme
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(600, 60));
        add(header, BorderLayout.NORTH);

        // Table columns
        String[] columns = { "Item ID", "Name", "Quantity", "Price" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setGridColor(Color.LIGHT_GRAY);

        // Table Header Styling
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 15));
        tableHeader.setBackground(new Color(0, 153, 255)); // Light Blue header
        tableHeader.setForeground(Color.WHITE);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Fetch data from DB
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM items";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                Object[] row = { id, name, quantity, price };
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load items.");
        }

        // Footer with 'Close' Button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        footerPanel.add(closeButton);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
