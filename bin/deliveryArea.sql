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
('AREA001', 'Downtown1', 'DP001', 20),
('AREA002', 'Uptown1', 'DP002', 15),
('AREA003', 'Midtown1', 'DP003', 10),
('AREA004', 'Downtown2', 'DP004', 20),
('AREA005', 'Uptown2', 'DP005', 15),
('AREA006', 'Midtown2', 'DP006', 10),
('AREA007', 'Downtown3', 'DP007', 20),
('AREA008', 'Uptown3', 'DP008', 15),
('AREA009', 'Midtown3', 'DP009', 10),
('AREA010', 'Downtown4', 'DP010', 20),
('AREA011', 'Uptown4', 'DP011', 15),
('AREA012', 'Midtown4', 'DP012', 10),
('AREA013', 'Downtown5', 'DP013', 20),
('AREA014', 'Uptown5', 'DP014', 15),
('AREA015', 'Midtown5', 'DP015', 10),
('AREA016', 'Downtown6', 'DP016', 20),
('AREA017', 'Uptown6', 'DP017', 15),
('AREA018', 'Midtown6', 'DP018', 10),
('AREA019', 'Downtown7', 'DP019', 20),
('AREA020', 'Uptown7', 'DP020', 15),
('AREA021', 'Midtown7', 'DP021', 10),
('AREA022', 'Downtown8', 'DP022', 20),
('AREA023', 'Uptown8', 'DP023', 15),
('AREA024', 'Midtown8', 'DP024', 10);	

Select * from delivery_areas;