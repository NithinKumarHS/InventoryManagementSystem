package gui;

import javax.swing.*;

import util.RoundedButton;

import java.awt.*;

public class DashboardFrame extends JFrame {
    private String role;

    public DashboardFrame(String role) {
        this.role = role;

        setTitle("Dashboard - Inventory Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0, 102, 204));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setPreferredSize(new Dimension(500, 60));
        add(headerLabel, BorderLayout.NORTH);

        // Welcome Label
        JLabel roleLabel = new JLabel("Logged in as: " + role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        roleLabel.setForeground(Color.DARK_GRAY);
        add(roleLabel, BorderLayout.SOUTH);

        // Center Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Common buttons
        JButton addItemBtn = createButton("Add Item","add.png");
        JButton updateItemBtn = createButton("Update Item","update.png");
        JButton searchItemBtn = createButton("Search Items","search.png");
        JButton reportBtn = createButton("Inventory Report","inventory.png");
        JButton undoBtn = createButton("Undo Last Operation","undo.png");
        JButton viewItemBtn = createButton("View Items","viewitems.png");
        JButton deleteItemBtn = createButton("Delete Item","delete.png");
        
        buttonPanel.add(addItemBtn);
        buttonPanel.add(viewItemBtn);
        buttonPanel.add(updateItemBtn);
        buttonPanel.add(searchItemBtn);
        
        

        if (role.equals("Admin")) {
            
            
            buttonPanel.add(deleteItemBtn);
            buttonPanel.add(undoBtn);
            buttonPanel.add(reportBtn);
            
            // Admin-specific actions
            
            deleteItemBtn.addActionListener(e -> new DeleteItemFrame());
            reportBtn.addActionListener(e -> new InventoryReportFrame());
        }
        
        
        JButton logoutBtn = createButton("Logout","logout.png");
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Event Listeners
        addItemBtn.addActionListener(e -> new AddItemFrame());
        viewItemBtn.addActionListener(e -> new ViewItemsFrame());
        updateItemBtn.addActionListener(e -> new UpdateItemFrame());
        searchItemBtn.addActionListener(e -> new SearchItemFrame());
        undoBtn.addActionListener(e -> UndoManager.undoLastOperation());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }
    private JButton createButton(String text, String iconPath) {

        ImageIcon rawIcon = new ImageIcon("icons/" + iconPath);
        Image scaledImage = rawIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);


        JButton button = new JButton(text, scaledIcon);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setVerticalTextPosition(SwingConstants.BOTTOM); 
        button.setHorizontalTextPosition(SwingConstants.CENTER); 
        button.setIconTextGap(5); 
        button.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        return new RoundedButton(button);
        
    }




}
	
