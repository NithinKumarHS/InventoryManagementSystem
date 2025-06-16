CREATE database inventory_db;

use inventory_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('Admin', 'Staff') NOT NULL
);

INSERT INTO users (username, password, role) VALUES
('admin', 'adminpass', 'Admin'),
('staff', 'staffpass', 'Staff');

CREATE TABLE items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    quantity INT,
    price DOUBLE
);

select * from items;

select * from users;

