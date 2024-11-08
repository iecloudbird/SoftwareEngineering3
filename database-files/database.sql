-- Create and use the newsagent database
CREATE DATABASE IF NOT EXISTS newsagent;
USE newsagent;

-- Drop all tables if they already exist to avoid conflicts
DROP TABLE IF EXISTS warning_letter;
DROP TABLE IF EXISTS storage;
DROP TABLE IF EXISTS Invoices;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS delivery_docket;
DROP TABLE IF EXISTS delivery_areas;
DROP TABLE IF EXISTS delivery_persons;

DROP TABLE IF EXISTS newsagent;
DROP TABLE IF EXISTS customers;
-- DROP TABLE IF EXISTS publications;

-- Create customers table (assuming it’s needed for foreign keys)
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(15) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    is_subscribed BOOLEAN NOT NULL
);

-- Create delivery_persons table
CREATE TABLE delivery_persons (
    delivery_person_id VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    assigned_area VARCHAR(100) NOT NULL,
    status ENUM('Out for delivery', 'Returned', 'Inactive') NOT NULL
);

-- Create publications table
CREATE TABLE publications (
    publication_id VARCHAR(20) PRIMARY KEY,
    publication_name VARCHAR(255) NOT NULL,
    publication_type VARCHAR(50) NOT NULL,
    publication_price DECIMAL(10, 2) NOT NULL,
    publication_frequency ENUM('Daily', 'Weekly', 'Monthly') NOT NULL
);

-- Create Invoices table
CREATE TABLE Invoices (
    invoice_id VARCHAR(50) PRIMARY KEY,
    cust_id INT NOT NULL,
    payment_method VARCHAR(60) NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    delivery_person_id VARCHAR(10) NOT NULL,
    publication_id VARCHAR(20) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_person_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

-- Create newsagent table
CREATE TABLE newsagent (
    agent_name VARCHAR(50) PRIMARY KEY,
    agent_address VARCHAR(50) NOT NULL,
    agent_phone VARCHAR(50) NOT NULL,
    agent_email VARCHAR(50) NOT NULL
);

-- Create orders table
CREATE TABLE orders (
    order_id VARCHAR(50) PRIMARY KEY,
    cust_id INT NOT NULL,
    delivery_id VARCHAR(10) NOT NULL,
    publication_id VARCHAR(20) NOT NULL,
    order_date DATETIME NOT NULL,
    order_status BOOLEAN NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

-- Create storage table
CREATE TABLE storage (
    storage_id VARCHAR(50) PRIMARY KEY,
    publication_id VARCHAR(20) NOT NULL,
    description_details BLOB,
    current_stock INT NOT NULL,
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

-- Create warning_letter table
CREATE TABLE warning_letter (
    letter_id VARCHAR(50) PRIMARY KEY,
    cust_id INT NOT NULL,
    cust_address VARCHAR(50) NOT NULL,
    reason VARCHAR(50) NOT NULL,
    due_amount DOUBLE NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Create Delivery Docket Table
CREATE TABLE delivery_docket (
    docket_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50),
    delivery_id VARCHAR(50),
    delivery_date DATETIME NOT NULL,
    delivery_status BOOLEAN NOT NULL,
    delivery_details VARCHAR(50)
);

-- Create Delivery Areas Table
CREATE TABLE delivery_areas (
    area_id VARCHAR(20) PRIMARY KEY,
    area_name VARCHAR(255) NOT NULL,
    delivery_person_id VARCHAR(10) NOT NULL,
    total_customers INT DEFAULT 0,
    FOREIGN KEY (delivery_person_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
);

-- Insert sample data into customers
INSERT INTO customers (customer_name, customer_address, customer_phone, customer_email, is_subscribed) VALUES
('John Doe', '123 Elm St, Springfield', '5551234567', 'john.doe@example.com', TRUE),
('Jane Smith', '456 Oak St, Springfield', '5559876543', 'jane.smith@example.com', FALSE),
('Alice Johnson', '789 Pine St, Springfield', '5555555555', 'alice.johnson@example.com', TRUE);

-- Insert sample data into delivery_persons
INSERT INTO delivery_persons (delivery_person_id, first_name, last_name, phone_number, assigned_area, status) VALUES
('DP001', 'Michael', 'Smith', '5551111111', 'Downtown1', 'Out for delivery'),
('DP002', 'Sarah', 'Johnson', '5552222222', 'Uptown1', 'Returned'),
('DP003', 'David', 'Brown', '5553333333', 'Midtown1', 'Inactive');

-- Insert sample data into publications
INSERT INTO publications (publication_id, publication_name, publication_type, publication_price, publication_frequency) VALUES
('PUB001', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB002', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB003', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly');

-- Insert sample data into Invoices
INSERT INTO Invoices (invoice_id, cust_id, payment_method, order_date, total_amount, delivery_person_id, publication_id, order_status) VALUES
('INV0001', 1, 'Credit Card', '2024-10-01', 150.00, 'DP001', 'PUB001', 'PENDING');

-- Insert sample data into newsagent
INSERT INTO newsagent (agent_name, agent_address, agent_phone, agent_email) VALUES
('Mike', 'Athlone', '0871234567', 'mike@newsagent.com');

-- Insert sample data into orders
INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES
('ORD0001', 1, 'DP001', 'PUB001', NOW(), TRUE),
('ORD0002', 2, 'DP002', 'PUB002', NOW(), FALSE),
('ORD0003', 3, 'DP003', 'PUB003', NOW(), TRUE);

-- Insert sample data into storage
INSERT INTO storage (storage_id, publication_id, description_details, current_stock) VALUES
('ST001', 'PUB001', 'Athlone Storage', 250),
('ST002', 'PUB002', 'Atlone Storage', 150);

-- Insert sample data into warning_letter
INSERT INTO warning_letter (letter_id, cust_id, cust_address, reason, due_amount) VALUES
('WL001', 1, 'Athlone', 'payment due', 90.00),
('WL002', 2, 'Dublin', 'payment due', 90.00),
('WL003', 3, 'Galway', 'payment due', 90.00);

-- Insert Data into Delivery Docket Table
INSERT INTO delivery_docket (docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details) VALUES
('DD001', 'ORD001', 'DP001', NOW(), TRUE, 'Delivered'),
('DD002', 'ORD002', 'DP002', NOW(), FALSE, 'Not Delivered'),
('DD003', 'ORD003', 'DP003', NOW(), TRUE, 'Delivered');

-- Insert Data into Delivery Areas Table
INSERT INTO delivery_areas (area_id, area_name, delivery_person_id, total_customers) VALUES
('AREA001', 'Downtown1', 'DP001', 20),
('AREA002', 'Uptown1', 'DP002', 15),
('AREA003', 'Midtown1', 'DP003', 10);

-- Select data to verify
SELECT * FROM Invoices;
SELECT * FROM newsagent;
SELECT * FROM orders;
SELECT * FROM publications;
SELECT * FROM storage;
SELECT * FROM warning_letter;
