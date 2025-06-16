//package gui;
//
//import db.DBConnection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//
//public class DeleteItemFrame extends JFrame {
//
//    private JTextField idField;
//
//    public DeleteItemFrame() {
//        setTitle("Delete Item");
//        setSize(300, 150);
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(3, 2, 5, 5));
//
//        add(new JLabel("Enter Item ID to Delete:"));
//        idField = new JTextField();
//        add(idField);
//
//        JButton deleteBtn = new JButton("Delete");
//        JButton cancelBtn = new JButton("Cancel");
//        add(deleteBtn);
//        add(cancelBtn);
//
//        deleteBtn.addActionListener(e -> deleteItem());
//        cancelBtn.addActionListener(e -> dispose());
//
//        setVisible(true);
//    }
//
//    private void deleteItem() {
//        int itemId;
//
//        try {
//            itemId = Integer.parseInt(idField.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid Item ID.");
//            return;
//        }
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm", JOptionPane.YES_NO_OPTION);
//
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "DELETE FROM items WHERE item_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, itemId);
//
//            int rows = ps.executeUpdate();
//
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "Item not found.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Deletion failed.");
//        }
//    }
//}

package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteItemFrame extends JFrame {

    private JTextField idField;

    public DeleteItemFrame() {
        setTitle("Delete Inventory Item");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Delete Item", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204)); // Red theme
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(400, 60));
        add(header, BorderLayout.NORTH);

        // Center Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        formPanel.setBackground(Color.WHITE);

        formPanel.add(new JLabel("Enter Item ID to Delete:"));
        idField = new JTextField();
        formPanel.add(idField);

        JButton deleteBtn = new JButton("Delete");
        JButton cancelBtn = new JButton("Cancel");
        formPanel.add(deleteBtn);
        formPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);

        // Button Actions
        deleteBtn.addActionListener(e -> deleteItem());
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

//    private void deleteItem() {
//        int itemId;
//
//        try {
//            itemId = Integer.parseInt(idField.getText().trim());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid Item ID.");
//            return;
//        }
//
//        int confirm = JOptionPane.showConfirmDialog(
//            this,
//            "Are you sure you want to delete this item?",
//            "Confirm Deletion",
//            JOptionPane.YES_NO_OPTION
//        );
//
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "DELETE FROM items WHERE item_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, itemId);
//
//            int rows = ps.executeUpdate();
//
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "Item not found.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Deletion failed.");
//        }
//    }
    private void deleteItem() {
        int itemId;

        try {
            itemId = Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Item ID.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this item?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection()) {
            // Step 1: Fetch item data before deletion
            String selectSql = "SELECT * FROM items WHERE item_id = ?";
            PreparedStatement selectPs = conn.prepareStatement(selectSql);
            selectPs.setInt(1, itemId);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                // Step 2: Record item info for undo
                UndoManager.recordDeletion(itemId, name, quantity, price);
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
                return;
            }

            // Step 3: Delete item
            String deleteSql = "DELETE FROM items WHERE item_id = ?";
            PreparedStatement deletePs = conn.prepareStatement(deleteSql);
            deletePs.setInt(1, itemId);

            int rows = deletePs.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Deletion failed.");
        }
    }
}

