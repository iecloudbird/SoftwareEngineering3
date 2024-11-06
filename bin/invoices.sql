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
    , delivery_persons VARCHAR(50) NOT NULL
	, publication_id VARCHAR(50) NOT NULL
    , order_status VARCHAR(50) NOT NULL 
);

-- Insert dummy data into Invoice table
INSERT INTO Invoices (invoice_id, cust_id, payment_method, order_date, total_amount
, delivery_persons, publication_id, order_status
) VALUES
('INV0001', 'CUST001', 'Credit Card', '2024-10-01', 150.00
, 'DP001', 'PUB001', 'PENDING'
); -- ,
-- ('INV0002', 'CUST002', 'Debit Card', '2024-10-02', 250.50),
-- ('INV0003', 'CUST003', 'PayPal', '2024-10-03', 75.20),
-- ('INV0004', 'CUST004', 'Bank Transfer', '2024-10-04', 200.00),
-- ('INV0005', 'CUST005', 'Credit Card', '2024-10-05', 120.75);

-- Query to select all invoices
SELECT * FROM Invoices;
