USE newsagent;

DROP TABLE IF EXISTS warning_letter;

-- Create warning_letter table
CREATE TABLE warning_letter (
	letter_id VARCHAR(50) PRIMARY KEY,
    cust_id INT NOT NULL,
    cust_address VARCHAR(50) NOT NULL,
    reason VARCHAR(50) NOT NULL,
    due_amount DOUBLE NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE
    -- FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
--     FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO warning_letter (letter_id, cust_id, cust_address, reason, due_amount) VALUES
('WL001', 1, 'Athlone', 'payment due', 90.00),
('WL002', 2, 'Dublin', 'payment due', 90.00),
('WL003', 3, 'Galway', 'payment due', 90.00),
('WL004', 1, 'Athlone', 'payment due', 90.00),
('WL005', 2, 'Dublin', 'payment due', 90.00),
('WL006', 3, 'Galway', 'payment due', 90.00),
('WL007', 1, 'Athlone', 'payment due', 90.00),
('WL008', 2, 'Dublin', 'payment due', 90.00),
('WL009', 3, 'Galway', 'payment due', 90.00),
('WL010', 1, 'Athlone', 'payment due', 90.00),
('WL011', 2, 'Dublin', 'payment due', 90.00),
('WL012', 3, 'Galway', 'payment due', 90.00),
('WL013', 1, 'Athlone', 'payment due', 90.00),
('WL014', 2, 'Dublin', 'payment due', 90.00),
('WL015', 3, 'Galway', 'payment due', 90.00),
('WL016', 1, 'Athlone', 'payment due', 90.00),
('WL017', 2, 'Dublin', 'payment due', 90.00),
('WL018', 3, 'Galway', 'payment due', 90.00),
('WL019', 1, 'Athlone', 'payment due', 90.00),
('WL020', 2, 'Dublin', 'payment due', 90.00),
('WL021', 3, 'Galway', 'payment due', 90.00),
('WL022', 1, 'Athlone', 'payment due', 90.00),
('WL023', 2, 'Dublin', 'payment due', 90.00),
('WL024', 3, 'Galway', 'payment due', 90.00);
Select * from warning_letter;