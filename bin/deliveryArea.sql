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
('AREA01', 'Downtown1', 'DP001', 20),
('AREA02', 'Uptown1', 'DP002', 15),
('AREA03', 'Midtown1', 'DP003', 10),
('AREA04', 'Downtown2', 'DP004', 20),
('AREA05', 'Uptown2', 'DP005', 15),
('AREA06', 'Midtown2', 'DP006', 10),
('AREA07', 'Downtown3', 'DP007', 20),
('AREA08', 'Uptown3', 'DP008', 15),
('AREA09', 'Midtown3', 'DP009', 10),
('AREA10', 'Downtown4', 'DP010', 20),
('AREA11', 'Uptown4', 'DP011', 15),
('AREA12', 'Midtown4', 'DP012', 10),
('AREA13', 'Downtown5', 'DP013', 20),
('AREA14', 'Uptown5', 'DP014', 15),
('AREA15', 'Midtown5', 'DP015', 10),
('AREA16', 'Downtown6', 'DP016', 20),
('AREA17', 'Uptown6', 'DP017', 15),
('AREA18', 'Midtown6', 'DP018', 10),
('AREA19', 'Downtown7', 'DP019', 20),
('AREA20', 'Uptown7', 'DP020', 15),
('AREA21', 'Midtown7', 'DP021', 10),
('AREA22', 'Downtown8', 'DP022', 20),
('AREA23', 'Uptown8', 'DP023', 15),
('AREA24', 'Midtown8', 'DP024', 10);	

Select * from delivery_areas;