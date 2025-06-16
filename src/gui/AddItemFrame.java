package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddItemFrame extends JFrame {

    private JTextField nameField, quantityField, priceField;

    public AddItemFrame() {
        setTitle("Add New Item - Inventory Management");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Add New Inventory Item", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(400, 60));
        add(header, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        formPanel.setBackground(new Color(245, 245, 245));

        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        quantityField = new JTextField();
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceField = new JTextField();
        priceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton addBtn = new JButton("Add Item");
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addBtn.setBackground(new Color(0, 153, 76));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelBtn.setBackground(new Color(204, 0, 0));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add components to panel
        formPanel.add(nameLabel); formPanel.add(nameField);
        formPanel.add(quantityLabel); formPanel.add(quantityField);
        formPanel.add(priceLabel); formPanel.add(priceField);
        formPanel.add(addBtn); formPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);

        // Action Listeners
        addBtn.addActionListener(e -> addItem());
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
    private void addItem() {
        String name = nameField.getText();
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers for quantity and price.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO items (name, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setDouble(3, price);
            
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated item ID
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int itemId = rs.getInt(1);
                    
                    // Record the addition for undo functionality
                    UndoManager.recordAddition(itemId);
                    
                    JOptionPane.showMessageDialog(this, "Item added successfully.");
                }
            }
            
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
