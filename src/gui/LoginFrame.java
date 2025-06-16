package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public LoginFrame() {
        setTitle("Inventory Management System - Login");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel
        JLabel headerLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0, 102, 204));
        headerLabel.setPreferredSize(new Dimension(500, 60));
        add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        String[] roles = {"Admin", "Staff"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add components to formPanel
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(userLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(passLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(roleLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; formPanel.add(roleComboBox, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(loginButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Login action
        loginButton.addActionListener(e -> login());

        setVisible(true);
    }
    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String selectedRole = (String) roleComboBox.getSelectedItem();

        try (Connection conn = db.DBConnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String dbRole = rs.getString("role");

                    if (dbRole.equals(selectedRole)) {
                        dispose(); // close login window
                        new DashboardFrame(dbRole); // launch dashboard
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Role mismatch. You are registered as '" + dbRole + "'.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Invalid username or password.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Database connection error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

