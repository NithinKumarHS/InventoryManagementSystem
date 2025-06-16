//package gui;
//
//import db.DBConnection;
//
//import java.sql.*;
//
//public class UndoManager {
//
//    private static String lastOperation = "";
//    private static int lastItemId = -1;
//
//    public static void recordAddition(int itemId) {
//        lastOperation = "add";
//        lastItemId = itemId;
//    }
//
//    public static void recordUpdate(int itemId) {
//        lastOperation = "update";
//        lastItemId = itemId;
//    }
//
//    public static void recordDeletion(int itemId) {
//        lastOperation = "delete";
//        lastItemId = itemId;
//    }
//
//    public static void undoLastOperation() {
//        if (lastOperation.equals("add")) {
//            undoAddition();
//        } else if (lastOperation.equals("update")) {
//            undoUpdate();
//        } else if (lastOperation.equals("delete")) {
//            undoDeletion();
//        } else {
//            System.out.println("No operation to undo.");
//        }
//    }
//
//    private static void undoAddition() {
//        try (Connection conn = DBConnection.getConnection()) {
//            String sql = "DELETE FROM items WHERE item_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, lastItemId);
//            ps.executeUpdate();
//            System.out.println("Undo: Item added was removed.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void undoUpdate() {
//        // Logic to revert the update (e.g., by restoring the previous data)
//        // This would require saving the previous state before updating an item
//        System.out.println("Undo: Item update reverted.");
//    }
//
//    private static void undoDeletion() {
//        // Logic to restore the deleted item (e.g., reinsert it with its previous data)
//        System.out.println("Undo: Item deletion reverted.");
//    }
//}
package gui;
import java.sql.*;

import javax.swing.JOptionPane;

import db.DBConnection;

public class UndoManager {
    public enum ActionType {
        ADD, UPDATE, DELETE
    }

    private static ActionType lastAction;
    private static int itemId;
    private static String name;
    private static int quantity;
    private static double price;

    // For UPDATE - store original data
    public static void recordUpdate(int id, String oldName, int oldQuantity, double oldPrice) {
        lastAction = ActionType.UPDATE;
        itemId = id;
        name = oldName;
        quantity = oldQuantity;
        price = oldPrice;
    }

    // For DELETE - store deleted item info
    public static void recordDeletion(int id, String itemName, int qty, double pr) {
        lastAction = ActionType.DELETE;
        itemId = id;
        name = itemName;
        quantity = qty;
        price = pr;
    }

    // For ADD - just store item ID
    public static void recordAddition(int id) {
        lastAction = ActionType.ADD;
        itemId = id;
    }

    public static void undoLastOperation() {
        if (lastAction == null) {
            JOptionPane.showMessageDialog(null, "No operation to undo.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            switch (lastAction) {
                case UPDATE:
                    String updateSql = "UPDATE items SET name = ?, quantity = ?, price = ? WHERE item_id = ?";
                    PreparedStatement updatePs = conn.prepareStatement(updateSql);
                    updatePs.setString(1, name);
                    updatePs.setInt(2, quantity);
                    updatePs.setDouble(3, price);
                    updatePs.setInt(4, itemId);
                    updatePs.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Undo Update: Restored previous item data.");
                    break;

                case DELETE:
                    String insertSql = "INSERT INTO items (item_id, name, quantity, price) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertPs = conn.prepareStatement(insertSql);
                    insertPs.setInt(1, itemId);
                    insertPs.setString(2, name);
                    insertPs.setInt(3, quantity);
                    insertPs.setDouble(4, price);
                    insertPs.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Undo Delete: Item restored.");
                    break;

                case ADD:
                    String deleteSql = "DELETE FROM items WHERE item_id = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setInt(1, itemId);
                    deletePs.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Undo Add: Item deleted.");
                    break;
            }

            // Reset last action
            lastAction = null;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Undo failed: " + e.getMessage());
        }
    }
}
