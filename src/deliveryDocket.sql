USE newsagent;

DROP TABLE IF EXISTS delivery_docket;

-- Create delivery_docket table
CREATE TABLE delivery_docket (
	docket_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50),
    delivery_id VARCHAR(50),
    delivery_date DATETIME NOT NULL,
    delivery_status BOOLEAN NOT NULL,
    delivery_details blob,
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES order_id(order_id) ON DELETE CASCADE
    -- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO delivery_docket (docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details) VALUES
('DD001', 'ORD001', 'DP001', NOW(), TRUE, 'Delivered'),
('DD002', 'ORD002', 'DP002', NOW(), FALSE, 'Not Delivered'),
('DD003', 'ORD003', 'DP003', NOW(), TRUE, 'Delivered');