-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS publications;

-- Create publications table
CREATE TABLE publications (
    publication_id VARCHAR(20) PRIMARY KEY,   -- Format: PUB003
    title VARCHAR(100) NOT NULL,              -- Title of the publication
    number_in_stocks INT NOT NULL,            -- Number of publications in stock
    price DECIMAL(10, 2) NOT NULL,            -- Price of the publication
    type ENUM('Newspaper', 'Magazine') NOT NULL, -- Type: Newspaper or Magazine
    delivery_frequency VARCHAR(50) NOT NULL    -- Frequency of delivery (Daily, Weekly, Monthly)
);


INSERT INTO publications (publication_id, title, number_in_stocks, price, type, delivery_frequency) VALUES
('PUB001', 'Daily News', 100, 2.00, 'Newspaper', 'Daily'),
('PUB002', 'Weekly Digest', 50, 5.00, 'Magazine', 'Weekly'),
('PUB003', 'Monthly Tech Review', 30, 10.00, 'Magazine', 'Monthly'),
('PUB004', 'Daily News', 120, 2.00, 'Newspaper', 'Daily'),
('PUB005', 'Weekly Digest', 60, 5.00, 'Magazine', 'Weekly'),
('PUB006', 'Monthly Tech Review', 40, 10.00, 'Magazine', 'Monthly'),
('PUB007', 'Daily News', 110, 2.00, 'Newspaper', 'Daily'),
('PUB008', 'Weekly Digest', 55, 5.00, 'Magazine', 'Weekly'),
('PUB009', 'Monthly Tech Review', 35, 10.00, 'Magazine', 'Monthly'),
('PUB010', 'Daily News', 115, 2.00, 'Newspaper', 'Daily'),
('PUB011', 'Weekly Digest', 58, 5.00, 'Magazine', 'Weekly'),
('PUB012', 'Monthly Tech Review', 45, 10.00, 'Magazine', 'Monthly'),
('PUB013', 'Daily News', 105, 2.00, 'Newspaper', 'Daily'),
('PUB014', 'Weekly Digest', 62, 5.00, 'Magazine', 'Weekly'),
('PUB015', 'Monthly Tech Review', 38, 10.00, 'Magazine', 'Monthly'),
('PUB016', 'Daily News', 130, 2.00, 'Newspaper', 'Daily'),
('PUB017', 'Weekly Digest', 52, 5.00, 'Magazine', 'Weekly'),
('PUB018', 'Monthly Tech Review', 42, 10.00, 'Magazine', 'Monthly'),
('PUB019', 'Daily News', 125, 2.00, 'Newspaper', 'Daily'),
('PUB020', 'Weekly Digest', 57, 5.00, 'Magazine', 'Weekly'),
('PUB021', 'Monthly Tech Review', 48, 10.00, 'Magazine', 'Monthly'),
('PUB022', 'Daily News', 140, 2.00, 'Newspaper', 'Daily'),
('PUB023', 'Weekly Digest', 63, 5.00, 'Magazine', 'Weekly'),
('PUB024', 'Monthly Tech Review', 50, 10.00, 'Magazine', 'Monthly');
Select * from publications;