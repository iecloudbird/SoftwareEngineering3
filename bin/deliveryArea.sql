-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS delivery_areas;

-- Create delivery_areas table
CREATE TABLE delivery_areas (
    area_id VARCHAR(20) PRIMARY KEY,
    area_name VARCHAR(255) NOT NULL,
    delivery_person_id VARCHAR(10) NOT NULL,
    total_customers INT DEFAULT 0,
    FOREIGN KEY (delivery_person_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
);


INSERT INTO delivery_areas (area_id, area_name, delivery_person_id, total_customers) VALUES
('AREA001', 'Downtown', 'DP001', 20),
('AREA002', 'Uptown', 'DP002', 15),
('AREA003', 'Midtown', 'DP003', 10);	