-- ==========================================================
-- MySQL Database Setup Script for Model v1
-- Run these queries in MySQL Workbench or MySQL Command Line
-- ==========================================================

-- 1. Create the database
CREATE DATABASE IF NOT EXISTS model_db;

-- 2. Switch to the database
USE model_db;

-- 3. Create 'users' table for login
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- 4. Insert default sample users (Username: admin, Password: admin123)
INSERT INTO users (username, password) VALUES 
('admin', 'admin123'),
('user', 'root')
ON DUPLICATE KEY UPDATE username=username;

-- 5. Create 'items' table for data records
CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    quantity INT NOT NULL DEFAULT 1,
    price DOUBLE NOT NULL DEFAULT 0.0,
    total DOUBLE NOT NULL DEFAULT 0.0
);

-- 6. Insert sample records with calculated total
INSERT INTO items (name, category, quantity, price, total) VALUES 
('Laptop', 'Electronics', 1, 750.00, 750.00),
('Desk Chair', 'Furniture', 2, 85.00, 170.00),
('Wireless Mouse', 'Electronics', 3, 25.00, 75.00);

-- 7. Verification: Check data
SELECT * FROM users;
SELECT * FROM items;
