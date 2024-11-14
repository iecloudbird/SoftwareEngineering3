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
('PUB003', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB004', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB005', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB006', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB007', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB008', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB009', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB010', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB011', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB012', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB013', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB014', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB015', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB016', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB017', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB018', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB019', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB020', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB021', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly'),
('PUB022', 'Daily News', 'Newspaper', 2.00, 'Daily'),
('PUB023', 'Weekly Digest', 'Magazine', 5.00, 'Weekly'),
('PUB024', 'Monthly Tech Review', 'Magazine', 10.00, 'Monthly');
Select * from publications;