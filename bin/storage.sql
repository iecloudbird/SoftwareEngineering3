USE newsagent;

DROP TABLE IF EXISTS storage;

-- Create storage table
CREATE TABLE storage (
    storage_id VARCHAR(50) PRIMARY KEY,
	publication_id VARCHAR(50) NOT NULL,
    description_details blob,
    -- capacity INT NOT NULL,
    current_stock INT NOT NULL,
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
--     FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO storage (storage_id, publication_id, desciption_details, current_stock) VALUES
('ST001', 'PUB001', 'Athlone Storage', 250),
('ST001', 'PUB002', 'Atlone Storage', 250);