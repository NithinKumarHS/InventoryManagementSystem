package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class InventoryReportFrame extends JFrame {

    public InventoryReportFrame() {
        setTitle("Inventory Report");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Inventory Report", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(600, 60));
        add(header, BorderLayout.NORTH);

        // Table
        JTable table = new JTable(new DefaultTableModel(new String[]{"Item ID", "Name", "Quantity", "Price", "Total Value"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Summary Panel
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3));
        JLabel totalItemsLabel = new JLabel();
        JLabel totalQtyLabel = new JLabel();
        JLabel totalValueLabel = new JLabel();
        summaryPanel.add(totalItemsLabel);
        summaryPanel.add(totalQtyLabel);
        summaryPanel.add(totalValueLabel);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(summaryPanel, BorderLayout.SOUTH);

        // Fetch data
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int totalItems = 0;
            int totalQty = 0;
            double totalValue = 0;

            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("name");
                int qty = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double value = qty * price;

                totalItems++;
                totalQty += qty;
                totalValue += value;

                model.addRow(new Object[]{id, name, qty, price, value});
            }

            totalItemsLabel.setText("Total Items: " + totalItems);
            totalQtyLabel.setText("Total Quantity: " + totalQty);
            totalValueLabel.setText(String.format("Inventory Value: ₹%.2f", totalValue));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load inventory report.");
        }

        setVisible(true);
    }
}
