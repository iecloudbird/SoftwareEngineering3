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
('WL003', 3, 'Galway', 'payment due', 90.00);