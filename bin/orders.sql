USE newsagent;

DROP TABLE IF EXISTS orders;

-- Create orders table
CREATE TABLE orders (
    order_id VARCHAR(50) PRIMARY KEY,
    cust_id INT NOT NULL,
    delivery_id VARCHAR(50) NOT NULL,
    publication_id VARCHAR(20) NOT NULL,
    order_date DATETIME NOT NULL,
    order_status BOOLEAN NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES
('ORD001', 1, 'DP001', 'PUB001', NOW(), TRUE),
('ORD002', 2, 'DP002', 'PUB002', NOW(), FALSE),
('ORD003', 3, 'DP003', 'PUB003', NOW(), TRUE);