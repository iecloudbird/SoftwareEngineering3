USE newsagent;

DROP TABLE IF EXISTS delivery_docket;

-- Create delivery_docket table
CREATE TABLE delivery_docket (
	docket_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50),
    delivery_person_id VARCHAR(50),
    delivery_date DATE DEFAULT CURRENT_DATE,
    delivery_status VARCHAR(50) NOT NULL,
    delivery_details VARCHAR(50),
    FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    -- FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
	-- FOREIGN KEY (order_id) REFERENCES order_id(order_id) ON DELETE CASCADE
    -- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);
	
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

SELECT * FROM delivery_docket;