-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(15) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    is_subscribed BOOLEAN NOT NULL
);


INSERT INTO customers (custname, custaddr, custphone, custemail, custsubscribed) VALUES
('John Doe', '123 Elm St, Springfield', '5551234567', 'john.doe@example.com', TRUE),
('Jane Smith', '456 Oak St, Springfield', '5559876543', 'jane.smith@example.com', FALSE),
('Alice Johnson', '789 Pine St, Springfield', '5555555555', 'alice.johnson@example.com', TRUE);