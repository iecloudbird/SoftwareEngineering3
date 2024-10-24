-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS publications;

-- Create publications table
CREATE TABLE publications (
    publication_id VARCHAR(20) PRIMARY KEY,
    publication_name VARCHAR(255) NOT NULL,
    publication_type VARCHAR(50) NOT NULL,
    publication_price DECIMAL(10, 2) NOT NULL,
    publication_frequency ENUM('Daily', 'Weekly', 'Monthly') NOT NULL
);


INSERT INTO publications (publication_id, publication_name, publication_type, publication_price, publication_frequency) VALUES
('PUB001', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB002', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB003', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly');