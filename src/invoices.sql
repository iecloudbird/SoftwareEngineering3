-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS Invoices;

-- Create Invoice table
CREATE TABLE Invoices (
    invoice_id VARCHAR(50) PRIMARY KEY,
    cust_id VARCHAR(60) NOT NULL,
    payment_method VARCHAR(60) NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL
    , delivery_docket VARCHAR(50) NOT NULL
	, publication_id VARCHAR(50) NOT NULL
    , order_status VARCHAR(50) NOT NULL 
);

-- Insert dummy data into Invoice table
INSERT INTO Invoices (invoice_id, cust_id, payment_method, order_date, total_amount
, delivery_docket, publication_id, order_status
) VALUES
('INV0001', '1', 'card', '2024-10-01', 150.00, 'DD00001', 'PUB001', 'PENDING'), -- ,
('INV0002', '2', 'Debit Card', '2024-10-02', 250.50, 'DD00002', 'PUB002', 'PENDING'),
('INV0003', '3', 'PayPal', '2024-10-03', 75.20, 'DD00003', 'PUB003', 'PENDING'),
('INV0004', '4', 'Bank Transfer', '2024-10-04', 200.00, 'DD00004', 'PUB004', 'PENDING'),
('INV0005', '5', 'Credit Card', '2024-10-05', 120.75, 'DD00005', 'PUB005', 'PENDING');

-- Query to select all invoices
SELECT * FROM Invoices;
