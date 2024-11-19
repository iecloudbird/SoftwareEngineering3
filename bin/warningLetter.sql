USE newsagent;

DROP TABLE IF EXISTS warning_letter;

-- Create warning_letter table
CREATE TABLE warning_letter (
	letter_id VARCHAR(50) PRIMARY KEY,
	order_id VARCHAR(50) NOT NULL, 
    cust_id INT NOT NULL,
    -- cust_address VARCHAR(50) NOT NULL,
    reason VARCHAR(50) NOT NULL,
    due_amount DOUBLE NOT NULL,
    issue_date DATE DEFAULT CURRENT_DATE, 
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE
    -- FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
--     FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO warning_letter (letter_id, order_id, cust_id, reason, due_amount, issue_date) VALUES
('WL001', 'ORD0001', 1, 'payment due', 90.00, '2024-11-01'),
('WL002', 'ORD0002', 2, 'payment due', 90.00, '2024-11-01'),
('WL003', 'ORD0003', 3, 'payment due', 90.00, '2024-11-01'),
('WL004', 'ORD0004', 1, 'payment due', 90.00, '2024-11-01'),
('WL005', 'ORD0005', 2, 'payment due', 90.00, '2024-11-01'),
('WL006', 'ORD0006', 3, 'payment due', 90.00, '2024-11-01'),
('WL007', 'ORD0007', 1, 'payment due', 90.00, '2024-11-01'),
('WL008', 'ORD0008', 2, 'payment due', 90.00, '2024-11-01'),
('WL009', 'ORD0009', 3, 'payment due', 90.00, '2024-11-01'),
('WL010', 'ORD0100', 1, 'payment due', 90.00, '2024-11-01'),
('WL011', 'ORD0011', 2, 'payment due', 90.00, '2024-11-01'),
('WL012', 'ORD0012', 3, 'payment due', 90.00, '2024-11-01'),
('WL013', 'ORD0013', 1, 'payment due', 90.00, '2024-11-01'),
('WL014', 'ORD0014', 2, 'payment due', 90.00, '2024-11-01'),
('WL015', 'ORD0105', 3, 'payment due', 90.00, '2024-11-01'),
('WL016', 'ORD0106', 1, 'payment due', 90.00, '2024-11-01'),
('WL017', 'ORD0017', 2, 'payment due', 90.00, '2024-11-01'),
('WL018', 'ORD0018', 3, 'payment due', 90.00, '2024-11-01'),
('WL019', 'ORD0019', 1, 'payment due', 90.00, '2024-11-01'),
('WL020', 'ORD0020', 2, 'payment due', 90.00, '2024-11-01'),
('WL021', 'ORD0021', 3, 'payment due', 90.00, '2024-11-01'),
('WL022', 'ORD0022', 1, 'payment due', 90.00, '2024-11-01'),
('WL023', 'ORD0023', 2, 'payment due', 90.00, '2024-11-01'),
('WL024', 'ORD0024', 3, 'payment due', 90.00, '2024-11-01');

Select * from warning_letter;