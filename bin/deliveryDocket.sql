USE newsagent;

DROP TABLE IF EXISTS delivery_docket;

-- Create delivery_docket table
CREATE TABLE delivery_docket (
	docket_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50),
    delivery_id VARCHAR(50),
    delivery_date DATETIME NOT NULL,
    delivery_status BOOLEAN NOT NULL,
    delivery_details VARCHAR(50)
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    -- FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
	-- FOREIGN KEY (order_id) REFERENCES order_id(order_id) ON DELETE CASCADE
    -- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO delivery_docket (docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details) VALUES
('DD001', 'ORD001', 'DP001', NOW(), TRUE, 'Delivered'),
('DD002', 'ORD002', 'DP002', NOW(), FALSE, 'Not Delivered'),
('DD003', 'ORD003', 'DP003', NOW(), TRUE, 'Delivered'),
('DD004', 'ORD004', 'DP004', NOW(), TRUE, 'Delivered'),
('DD005', 'ORD005', 'DP005', NOW(), FALSE, 'Not Delivered'),
('DD006', 'ORD006', 'DP006', NOW(), TRUE, 'Delivered'),
('DD007', 'ORD007', 'DP007', NOW(), TRUE, 'Delivered'),
('DD008', 'ORD008', 'DP008', NOW(), FALSE, 'Not Delivered'),
('DD009', 'ORD009', 'DP009', NOW(), TRUE, 'Delivered'),
('DD010', 'ORD010', 'DP010', NOW(), TRUE, 'Delivered'),
('DD011', 'ORD011', 'DP011', NOW(), FALSE, 'Not Delivered'),
('DD012', 'ORD012', 'DP012', NOW(), TRUE, 'Delivered'),
('DD013', 'ORD013', 'DP013', NOW(), TRUE, 'Delivered'),
('DD014', 'ORD014', 'DP014', NOW(), FALSE, 'Not Delivered'),
('DD015', 'ORD015', 'DP015', NOW(), TRUE, 'Delivered'),
('DD016', 'ORD016', 'DP016', NOW(), TRUE, 'Delivered'),
('DD017', 'ORD017', 'DP017', NOW(), FALSE, 'Not Delivered'),
('DD018', 'ORD018', 'DP018', NOW(), TRUE, 'Delivered'),
('DD019', 'ORD019', 'DP019', NOW(), TRUE, 'Delivered'),
('DD020', 'ORD020', 'DP020', NOW(), FALSE, 'Not Delivered'),
('DD021', 'ORD021', 'DP021', NOW(), TRUE, 'Delivered'),
('DD022', 'ORD022', 'DP022', NOW(), TRUE, 'Delivered'),
('DD023', 'ORD023', 'DP023', NOW(), FALSE, 'Not Delivered'),
('DD024', 'ORD024', 'DP024', NOW(), TRUE, 'Delivered');

SELECT * FROM delivery_docket;