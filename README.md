# 📦 Inventory Management System – Advance Java Project

<div align="center">
  <img src="https://img.shields.io/badge/Java-Advanced%20Java-blue" alt="Java">
  <img src="https://img.shields.io/badge/Swing-Java%20GUI-orange" alt="Swing">
  <img src="https://img.shields.io/badge/MySQL-8.0%2B-lightgrey" alt="MySQL">
</div>

An advanced desktop-based Inventory Management System built using Java (Swing, and JDBC) with MySQL as the backend. Designed to manage stock, control access with roles, and generate reports.

---

## 🧠 Project Overview

This Inventory Management System is built for small businesses and internal company use to streamline item stock, purchase, and reporting. It provides:

- Role-based dashboards (Admin & Staff)
- CRUD operations for inventory items
- Report generation 
- Undo last operation
- Real-time search

---

## Table of Contents

- [Project Overview](#-project-overview)
- [Project Structure](#-project-structure)
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [Database Schema](#-database-schema)
- [Technologies Used](#-technologies-used)
- [Screenshots](#-screenshots)
- [Future Enhancements](#-future-enhancements)
- [Author](#-author)

---

## 📁 Project Structure

```text
InventoryManagementSystem/
├── src/
│   ├── com/                         # All Java source files
│   │   ├── db/                      # DB connections # Login/Signup logic
│   │   ├── gui/                     # All the frames of the project
│   │   ├── models/                  # Getter and Setters
│   │   ├── services/                # MySQL DB schema and sample data
│   │   └── utils/                   # Helper Classes
│   ├── Main.java                    # Main Runnable class to start the project
│
├── lib/                             # MySQL JDBC Connector
├── icons/                           # App icons
├── README.md           
````

---

## 🚀 Features

* 🔐 **Role-based Access**: Admin and Staff dashboards
* 📋 **Inventory Control**: Add, edit, delete, and view products
* 📈 **Reports**: Generate item-level reports
* 🔍 **Search**: Real-time search by name/ID
* ↩️ **Undo Last Operation**: Revert the most recent change
* 🧩 **User-friendly Interface**: Built with Java Swing for smooth interaction

---

## ⚙️ Installation

1. **Clone the repository**

```bash
git clone https://github.com/NithinKumarHS/InventoryManagementSystem.git
cd InventoryManagementSystem
```

2. **Set up MySQL Database**

   * Import `inventory_db.sql` into MySQL
   * Update DB credentials in the `DBConnection.java` file if needed

3. **Compile and Run**

   * Use any IDE like Eclipse or IntelliJ
   * Set the `src/` folder as the source root
   * Build and run `Main.java` to start

---

## 💻 Usage

* Login as Admin or Staff
* Navigate to:

  * 📦 Inventory → Manage items
  * 📊 Reports → View item or user logs
  * ⚙️ Settings → Update credentials (Admin only)

---

## 🗃️ Database Schema

**Tables:**

* `users` – stores admin/staff credentials and roles
* `items` – product name, quantity, price, category

---

## 🔧 Technologies Used

* Java (Core + Advanced)
* Java Swing (GUI)
* JDBC (Java Database Connectivity)
* MySQL
* OOPs, Exception Handling, Collections
* MVC Architecture

---

## 📸 Screenshots

<table align="center">
  <tr>
    <td align="center">
      <img src="Results/Login Page.png" alt="Login Page" width="400"/><br/>
      <strong>Login Page</strong>
    </td>
    <td align="center">
      <img src="Results/Admin Login.png" alt="Admin Dashboard" width="400"/><br/>
      <strong>Admin Dashboard</strong>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="Results/Staff Login.png" alt="Staff Dashboard" width="400"/><br/>
      <strong>Staff Dashboard</strong>
    </td>
    <td align="center">
      <img src="Results/Add Item.png" alt="Add Item Page" width="400"/><br/>
      <strong>Add Item</strong>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="Results/Delete Item.png" alt="Delete Item Page" width="400"/><br/>
      <strong>Delete Item</strong>
    </td>
    <td align="center">
      <img src="Results/Search Item.png" alt="Search Item Page" width="400"/><br/>
      <strong>Search Item</strong>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="Results/View Item.png" alt="View Item Page" width="400"/><br/>
      <strong>View Items</strong>
    </td>
    <td align="center">
      <img src="Results/Update Item.png" alt="Update Item Page" width="400"/><br/>
      <strong>Update Item</strong>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="Results/Inventory Report.png" alt="Inventory Report Page" width="400"/><br/>
      <strong>Inventory Report</strong>
    </td>
    <td align="center">
      <img src="Results/Undo Operation.png" alt="Undo Operation Page" width="400"/><br/>
      <strong>Undo Operation</strong>
    </td>
  </tr>
</table>


---

## 🔮 Future Enhancements

* Add PDF export for reports
* Enable barcode scanning integration
* Implement email-based notifications
* Role management (add custom roles and permissions)
* Web-based version using JSP/Servlets or Spring Boot

---

## 👤 Author

**Nithin Kumar HS**  
GitHub: [@NithinKumarHS](https://github.com/NithinKumarHS)  
