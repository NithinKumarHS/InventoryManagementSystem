//package gui;
//
//import db.DBConnection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//
//public class UpdateItemFrame extends JFrame {
//
//    private JTextField idField, nameField, quantityField, priceField;
//
//    public UpdateItemFrame() {
//        setTitle("Update Item");
//        setSize(350, 300);
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(6, 2, 5, 5));
//
//        add(new JLabel("Enter Item ID:"));
//        idField = new JTextField();
//        add(idField);
//
//        JButton loadBtn = new JButton("Load Item");
//        add(loadBtn);
//        add(new JLabel()); // empty space
//
//        add(new JLabel("Name:"));
//        nameField = new JTextField();
//        add(nameField);
//
//        add(new JLabel("Quantity:"));
//        quantityField = new JTextField();
//        add(quantityField);
//
//        add(new JLabel("Price:"));
//        priceField = new JTextField();
//        add(priceField);
//
//        JButton updateBtn = new JButton("Update");
//        JButton cancelBtn = new JButton("Cancel");
//        add(updateBtn);
//        add(cancelBtn);
//
//        loadBtn.addActionListener(e -> loadItem());
//        updateBtn.addActionListener(e -> updateItem());
//        cancelBtn.addActionListener(e -> dispose());
//
//        setVisible(true);
//    }
//
//    private void loadItem() {
//        int itemId;
//
//        try {
//            itemId = Integer.parseInt(idField.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Enter a valid Item ID.");
//            return;
//        }
//
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "SELECT * FROM items WHERE item_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, itemId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                nameField.setText(rs.getString("name"));
//                quantityField.setText(String.valueOf(rs.getInt("quantity")));
//                priceField.setText(String.valueOf(rs.getDouble("price")));
//            } else {
//                JOptionPane.showMessageDialog(this, "Item not found.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Failed to load item.");
//        }
//    }
//
//    private void updateItem() {
//        int itemId;
//        String name = nameField.getText();
//        int quantity;
//        double price;
//
//        try {
//            itemId = Integer.parseInt(idField.getText());
//            quantity = Integer.parseInt(quantityField.getText());
//            price = Double.parseDouble(priceField.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Enter valid values.");
//            return;
//        }
//
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "UPDATE items SET name = ?, quantity = ?, price = ? WHERE item_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setInt(2, quantity);
//            ps.setDouble(3, price);
//            ps.setInt(4, itemId);
//            int rows = ps.executeUpdate();
//
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Item updated successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "Item not found.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Update failed.");
//        }
//    }
//}

package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateItemFrame extends JFrame {

    private JTextField idField, nameField, quantityField, priceField;

    public UpdateItemFrame() {
        setTitle("Update Inventory Item");
        setSize(400, 370);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Update Item", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204)); // Greenish tone
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(400, 60));
        add(header, BorderLayout.NORTH);

        // Center Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        formPanel.setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("Enter Item ID:");
        idField = new JTextField();
        JButton loadBtn = new JButton("Load Item");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(loadBtn);
        formPanel.add(new JLabel()); // Empty space

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");
        formPanel.add(updateBtn);
        formPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);

        // Event Listeners
        loadBtn.addActionListener(e -> loadItem());
        updateBtn.addActionListener(e -> updateItem());
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void loadItem() {
        int itemId;

        try {
            itemId = Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid Item ID.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM items WHERE item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                quantityField.setText(String.valueOf(rs.getInt("quantity")));
                priceField.setText(String.valueOf(rs.getDouble("price")));
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load item.");
        }
    }

    private void updateItem() {
        int itemId;
        String name = nameField.getText().trim();
        int quantity;
        double price;

        try {
            itemId = Integer.parseInt(idField.getText().trim());
            quantity = Integer.parseInt(quantityField.getText().trim());
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter valid values.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Step 1: Retrieve current item data before updating
            String selectSql = "SELECT * FROM items WHERE item_id = ?";
            PreparedStatement selectPs = conn.prepareStatement(selectSql);
            selectPs.setInt(1, itemId);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                String oldName = rs.getString("name");
                int oldQty = rs.getInt("quantity");
                double oldPrice = rs.getDouble("price");

                // Step 2: Record the old data
                UndoManager.recordUpdate(itemId, oldName, oldQty, oldPrice);
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
                return;
            }

            // Step 3: Update the item
            String updateSql = "UPDATE items SET name = ?, quantity = ?, price = ? WHERE item_id = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateSql);
            updatePs.setString(1, name);
            updatePs.setInt(2, quantity);
            updatePs.setDouble(3, price);
            updatePs.setInt(4, itemId);

            int rows = updatePs.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Item updated successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update failed.");
        }
    }

}

