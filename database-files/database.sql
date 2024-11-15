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
DROP TABLE IF EXISTS publications;

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
    publication_id VARCHAR(20) PRIMARY KEY,   -- Format: PUB003
    title VARCHAR(100) NOT NULL,              -- Title of the publication
    number_in_stocks INT NOT NULL,            -- Number of publications in stock
    price DECIMAL(10, 2) NOT NULL,            -- Price of the publication
    type ENUM('Newspaper', 'Magazine') NOT NULL, -- Type: Newspaper or Magazine
    delivery_frequency VARCHAR(50) NOT NULL    -- Frequency of delivery (Daily, Weekly, Monthly)
);

-- Create Invoices table
CREATE TABLE Invoices (
    invoice_id VARCHAR(50) PRIMARY KEY,
   	cust_id INT NOT NULL,
    payment_method ENUM('card', 'cash') NOT NULL,
    order_date DATE DEFAULT CURRENT_DATE,
    total_amount DECIMAL(10, 2) NOT NULL, 
    delivery_docket_id VARCHAR(50) NOT NULL, 
    publication_id VARCHAR(20) NOT NULL, 
    order_status VARCHAR(50) NOT NULL
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    -- FOREIGN KEY (delivery_docket_id) REFERENCES delivery_docket(docket_id) ON DELETE CASCADE,
    -- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
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
    delivery_id VARCHAR(50) NOT NULL, 
    publication_id VARCHAR(20) NOT NULL, 
    order_date DATE DEFAULT CURRENT_DATE, 
    order_status VARCHAR(20) NOT NULL 
	-- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
	-- FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
  	-- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
    ); 

-- Create storage table
CREATE TABLE storage (
    storage_id VARCHAR(50) PRIMARY KEY,
	publication_id VARCHAR(50) NOT NULL,
    description_details blob,
    capacity INT NOT NULL,
    current_stock INT NOT NULL,
   	FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

-- Create warning_letter table
CREATE TABLE warning_letter (
	letter_id VARCHAR(50) PRIMARY KEY,
	order_id VARCHAR(50) NOT NULL, 
    cust_id INT NOT NULL,
    reason VARCHAR(50) NOT NULL,
    due_amount DOUBLE NOT NULL,
    issue_date DATE DEFAULT CURRENT_DATE, 
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Create Delivery Docket Table
CREATE TABLE delivery_docket (
	docket_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50),
    delivery_person_id VARCHAR(10),
    delivery_date DATE DEFAULT CURRENT_DATE,
    delivery_status VARCHAR(50) NOT NULL,
    delivery_details VARCHAR(50),
    FOREIGN KEY (delivery_person_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
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
('DP003', 'David', 'Brown', '5553333333', 'Midtown1', 'Inactive'),
('DP004', 'Brandon', 'Galen', '5554444444', 'Downtown2', 'Out for delivery'),
('DP005', 'Lawrence', 'Zella', '5555555555', 'Uptown2', 'Returned'),
('DP006', 'Fuller', 'Drina', '5556666666', 'Midtown2', 'Inactive'),
('DP007', 'Neville', 'Trueman', '5557777777', 'Downtown3', 'Out for delivery'),
('DP008', 'Beckham', 'Ardath', '5558888888', 'Uptown3', 'Returned'),
('DP009', 'Harvey', 'Rupert', '5559999999', 'Midtown3', 'Inactive'),
('DP010', 'Rake', 'Sparrow', '6660000000', 'Downtown4', 'Out for delivery'),
('DP011', 'Browne', 'Mathilda', '6661111111', 'Uptown4', 'Returned'),
('DP012', 'Cornett', 'Elicia', '6662222222', 'Midtown4', 'Inactive'),
('DP013', 'Lazarus', 'Derby', '6663333333', 'Downtown5', 'Out for delivery'),
('DP014', 'Candi', 'Beech', '6664444444', 'Uptown5', 'Returned'),
('DP015', 'Brent', 'Peters', '6665555555', 'Midtown5', 'Inactive'),
('DP016', 'Gabrielson', 'Deeann', '6666666666', 'Downtown6', 'Out for delivery'),
('DP017', 'Albertson', 'Kemp', '6667777777', 'Uptown6', 'Returned'),
('DP018', 'Laney', 'Rigby', '6668888888', 'Midtown6', 'Inactive'),
('DP019', 'Thurstan', 'Walton', '6669999999', 'Downtown7', 'Out for delivery'),
('DP020', 'Tobias', 'Hettie', '7770000000', 'Uptown7', 'Returned'),
('DP021', 'Miller', 'Rosalind', '7771111111', 'Midtown7', 'Inactive'),
('DP022', 'Bryant', 'Jessie', '7772222222', 'Downtown8', 'Out for delivery'),
('DP023', 'Post', 'Bazza', '7773333333', 'Uptown8', 'Returned'),
('DP024', 'Graves', 'Sherman', '7774444444', 'Midtown8', 'Inactive');

-- Insert sample data into publications
INSERT INTO publications (publication_id, title, number_in_stocks, price, type, delivery_frequency) VALUES
('PUB001', 'Daily News', 100, 2.00, 'Newspaper', 'Daily'),
('PUB002', 'Weekly Digest', 50, 5.00, 'Magazine', 'Weekly'),
('PUB003', 'Monthly Tech Review', 30, 10.00, 'Magazine', 'Monthly'),
('PUB004', 'Daily News', 120, 2.00, 'Newspaper', 'Daily'),
('PUB005', 'Weekly Digest', 60, 5.00, 'Magazine', 'Weekly'),
('PUB006', 'Monthly Tech Review', 40, 10.00, 'Magazine', 'Monthly'),
('PUB007', 'Daily News', 110, 2.00, 'Newspaper', 'Daily'),
('PUB008', 'Weekly Digest', 55, 5.00, 'Magazine', 'Weekly'),
('PUB009', 'Monthly Tech Review', 35, 10.00, 'Magazine', 'Monthly'),
('PUB010', 'Daily News', 115, 2.00, 'Newspaper', 'Daily'),
('PUB011', 'Weekly Digest', 58, 5.00, 'Magazine', 'Weekly'),
('PUB012', 'Monthly Tech Review', 45, 10.00, 'Magazine', 'Monthly'),
('PUB013', 'Daily News', 105, 2.00, 'Newspaper', 'Daily'),
('PUB014', 'Weekly Digest', 62, 5.00, 'Magazine', 'Weekly'),
('PUB015', 'Monthly Tech Review', 38, 10.00, 'Magazine', 'Monthly'),
('PUB016', 'Daily News', 130, 2.00, 'Newspaper', 'Daily'),
('PUB017', 'Weekly Digest', 52, 5.00, 'Magazine', 'Weekly'),
('PUB018', 'Monthly Tech Review', 42, 10.00, 'Magazine', 'Monthly'),
('PUB019', 'Daily News', 125, 2.00, 'Newspaper', 'Daily'),
('PUB020', 'Weekly Digest', 57, 5.00, 'Magazine', 'Weekly'),
('PUB021', 'Monthly Tech Review', 48, 10.00, 'Magazine', 'Monthly'),
('PUB022', 'Daily News', 140, 2.00, 'Newspaper', 'Daily'),
('PUB023', 'Weekly Digest', 63, 5.00, 'Magazine', 'Weekly'),
('PUB024', 'Monthly Tech Review', 50, 10.00, 'Magazine', 'Monthly');

-- Insert sample data into Invoices
INSERT INTO Invoices (invoice_id, cust_id, payment_method, order_date, total_amount
, delivery_docket_id, publication_id, order_status
) VALUES
('INV0001', '1', 'card', '2024-10-01', 150.00, 'DD00001', 'PUB001', 'PENDING'), -- ,
('INV0002', '2', 'card', '2024-10-02', 250.50, 'DD00002', 'PUB002', 'PENDING'),
('INV0003', '3', 'card', '2024-10-03', 75.20, 'DD00003', 'PUB003', 'PENDING'),
('INV0004', '4', 'card', '2024-10-04', 200.00, 'DD00004', 'PUB004', 'PENDING'),
('INV0005', '5', 'cash', '2024-10-05', 120.75, 'DD00005', 'PUB005', 'PENDING');


-- Insert sample data into newsagent
INSERT INTO newsagent (agent_name, agent_address, agent_phone, agent_email) VALUES
('Mike', 'Athlone', '0871234567', 'mike@newsagent.com');

-- Insert sample data into orders
INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES
('ORD0001', 1, 'AREA01', 'PUB001', NOW(), 'PENDING'),  -- New order with status PENDING
('ORD0002', 2, 'AREA02', 'PUB002', NOW(), 'CONFIRMED'),  -- Order confirmed
('ORD0003', 3, 'AREA03', 'PUB003', NOW(), 'DISPATCHED');  -- Order dispatched

-- Insert sample data into storage
INSERT INTO storage (storage_id, publication_id, description_details,capacity, current_stock) VALUES
('ST001', 'PUB001', 'Athlone Storage',300, 250),
('ST002', 'PUB002', 'Atlone Storage', 300, 250);

-- Insert sample data into warning_letter
INSERT INTO warning_letter (letter_id, order_id, cust_id, reason, due_amount, issue_date) VALUES
('WL001', 'ORD001', 1, 'payment due', 90.00, '2024-11-01'),
('WL002', 'ORD002', 2, 'payment due', 90.00, '2024-11-01'),
('WL003', 'ORD003', 3, 'payment due', 90.00, '2024-11-01'),
('WL004', 'ORD004', 1, 'payment due', 90.00, '2024-11-01'),
('WL005', 'ORD005', 2, 'payment due', 90.00, '2024-11-01'),
('WL006', 'ORD006', 3, 'payment due', 90.00, '2024-11-01'),
('WL007', 'ORD007', 1, 'payment due', 90.00, '2024-11-01'),
('WL008', 'ORD008', 2, 'payment due', 90.00, '2024-11-01'),
('WL009', 'ORD009', 3, 'payment due', 90.00, '2024-11-01'),
('WL010', 'ORD010', 1, 'payment due', 90.00, '2024-11-01'),
('WL011', 'ORD011', 2, 'payment due', 90.00, '2024-11-01'),
('WL012', 'ORD012', 3, 'payment due', 90.00, '2024-11-01'),
('WL013', 'ORD013', 1, 'payment due', 90.00, '2024-11-01'),
('WL014', 'ORD014', 2, 'payment due', 90.00, '2024-11-01'),
('WL015', 'ORD015', 3, 'payment due', 90.00, '2024-11-01'),
('WL016', 'ORD016', 1, 'payment due', 90.00, '2024-11-01'),
('WL017', 'ORD017', 2, 'payment due', 90.00, '2024-11-01'),
('WL018', 'ORD018', 3, 'payment due', 90.00, '2024-11-01'),
('WL019', 'ORD019', 1, 'payment due', 90.00, '2024-11-01'),
('WL020', 'ORD020', 2, 'payment due', 90.00, '2024-11-01'),
('WL021', 'ORD021', 3, 'payment due', 90.00, '2024-11-01'),
('WL022', 'ORD022', 1, 'payment due', 90.00, '2024-11-01'),
('WL023', 'ORD023', 2, 'payment due', 90.00, '2024-11-01'),
('WL024', 'ORD024', 3, 'payment due', 90.00, '2024-11-01');

-- Insert Data into Delivery Docket Table
INSERT INTO delivery_docket (docket_id, order_id, delivery_person_id, delivery_date, delivery_status, delivery_details) VALUES
('DD001', 'ORD0001', 'DP001', NOW(), 'Delivered', 'Delivered'),
('DD002', 'ORD0002', 'DP002', NOW(), 'Not Delivered', 'Not Delivered'),
('DD003', 'ORD0003', 'DP003', NOW(), 'Delivered', 'Delivered'),
('DD004', 'ORD0004', 'DP004', NOW(), 'Delivered', 'Delivered'),
('DD005', 'ORD0005', 'DP005', NOW(), 'Not Delivered', 'Not Delivered'),
('DD006', 'ORD0006', 'DP006', NOW(), 'Delivered', 'Delivered'),
('DD007', 'ORD0007', 'DP007', NOW(), 'Delivered', 'Delivered'),
('DD008', 'ORD0008', 'DP008', NOW(), 'Not Delivered', 'Not Delivered'),
('DD009', 'ORD0009', 'DP009', NOW(), 'Delivered', 'Delivered'),
('DD010', 'ORD0010', 'DP010', NOW(), 'Delivered', 'Delivered'),
('DD011', 'ORD0011', 'DP011', NOW(), 'Not Delivered', 'Not Delivered'),
('DD012', 'ORD0012', 'DP012', NOW(), 'Delivered', 'Delivered'),
('DD013', 'ORD0013', 'DP013', NOW(), 'Delivered', 'Delivered'),
('DD014', 'ORD0014', 'DP014', NOW(), 'Not Delivered', 'Not Delivered'),
('DD015', 'ORD0015', 'DP015', NOW(), 'Delivered', 'Delivered'),
('DD016', 'ORD0016', 'DP016', NOW(), 'Delivered', 'Delivered'),
('DD017', 'ORD0017', 'DP017', NOW(), 'Not Delivered', 'Not Delivered'),
('DD018', 'ORD0018', 'DP018', NOW(), 'Delivered', 'Delivered'),
('DD019', 'ORD0019', 'DP019', NOW(), 'Delivered', 'Delivered'),
('DD020', 'ORD0020', 'DP020', NOW(), 'Not Delivered', 'Not Delivered'),
('DD021', 'ORD0021', 'DP021', NOW(), 'Delivered', 'Delivered'),
('DD022', 'ORD0022', 'DP022', NOW(), 'Delivered', 'Delivered'),
('DD023', 'ORD0023', 'DP023', NOW(), 'Not Delivered', 'Not Delivered'),
('DD024', 'ORD0024', 'DP024', NOW(), 'Delivered', 'Delivered');

-- Insert Data into Delivery Areas Table
INSERT INTO delivery_areas (area_id, area_name, delivery_person_id, total_customers) VALUES
('AREA01', 'Downtown1', 'DP001', 20),
('AREA02', 'Uptown1', 'DP002', 15),
('AREA03', 'Midtown1', 'DP003', 10),
('AREA04', 'Downtown2', 'DP004', 20),
('AREA05', 'Uptown2', 'DP005', 15),
('AREA06', 'Midtown2', 'DP006', 10),
('AREA07', 'Downtown3', 'DP007', 20),
('AREA08', 'Uptown3', 'DP008', 15),
('AREA09', 'Midtown3', 'DP009', 10),
('AREA10', 'Downtown4', 'DP010', 20),
('AREA11', 'Uptown4', 'DP011', 15),
('AREA12', 'Midtown4', 'DP012', 10),
('AREA13', 'Downtown5', 'DP013', 20),
('AREA14', 'Uptown5', 'DP014', 15),
('AREA15', 'Midtown5', 'DP015', 10),
('AREA16', 'Downtown6', 'DP016', 20),
('AREA17', 'Uptown6', 'DP017', 15),
('AREA18', 'Midtown6', 'DP018', 10),
('AREA19', 'Downtown7', 'DP019', 20),
('AREA20', 'Uptown7', 'DP020', 15),
('AREA21', 'Midtown7', 'DP021', 10),
('AREA22', 'Downtown8', 'DP022', 20),
('AREA23', 'Uptown8', 'DP023', 15),
('AREA24', 'Midtown8', 'DP024', 10);	

-- Select data to verify
SELECT * FROM Invoices;
SELECT * FROM newsagent;
SELECT * FROM orders;
SELECT * FROM publications;
SELECT * FROM storage;
SELECT * FROM warning_letter;
