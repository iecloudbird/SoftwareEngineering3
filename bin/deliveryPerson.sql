-- create database newsagent; 

-- use newsagent;

-- DROP TABLE IF EXISTS delivery_persons;

-- Create delivery_persons table
-- CREATE TABLE delivery_persons (
--     delivery_person_id VARCHAR(10) PRIMARY KEY,
--     first_name VARCHAR(15) NOT NULL,
--     last_name VARCHAR(25) NOT NULL,
--     phone_number CHAR(10) NOT NULL,
--     assigned_area VARCHAR(100) NOT NULL,
--     status ENUM('Out for delivery', 'Returned', 'Inactive') NOT NULL
-- );


-- INSERT INTO delivery_persons (delivery_person_id, first_name, last_name, phone_number, assigned_area, status) VALUES
-- ('DP001', 'Michael', 'Smith', '5551111111', 'Downtown', 'Out for delivery'),
-- ('DP002', 'Sarah', 'Johnson', '5552222222', 'Uptown', 'Returned'),
-- ('DP003', 'David', 'Brown', '5553333333', 'Midtown', 'Inactive');
Select * from delivery_persons;