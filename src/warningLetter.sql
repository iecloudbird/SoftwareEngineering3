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

Select * from warning_letter;