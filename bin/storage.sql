USE newsagent;

DROP TABLE IF EXISTS storage;

-- Create storage table
CREATE TABLE storage (
    storage_id VARCHAR(50) PRIMARY KEY,
	publication_id VARCHAR(50) NOT NULL,
    description_details blob,
    capacity INT NOT NULL,
    current_stock INT NOT NULL,
   	FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO storage (storage_id, publication_id, description_details,capacity, current_stock) VALUES
('ST001', 'PUB001', 'Athlone Storage',300, 250),
('ST002', 'PUB002', 'Atlone Storage', 300, 250);