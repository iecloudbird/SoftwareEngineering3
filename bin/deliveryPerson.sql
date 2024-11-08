-- create database newsagent; 

use newsagent;

DROP TABLE IF EXISTS delivery_persons;

-- Create delivery_persons table
CREATE TABLE delivery_persons (
    delivery_person_id VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    assigned_area VARCHAR(100) NOT NULL,
    status ENUM('Out for delivery', 'Returned', 'Inactive') NOT NULL ,
);

    
INSERT INTO delivery_persons (delivery_person_id, first_name, last_name, phone_number, assigned_area, status) VALUES
('DP001', 'Michael', 'Smith', '5551111111', 'Downtown1', 'Out for delivery');
-- ('DP002', 'Sarah', 'Johnson', '5552222222', 'Uptown1', 'Returned'),
-- ('DP003', 'David', 'Brown', '5553333333', 'Midtown1', 'Inactive'),
-- ('DP004', 'Brandon', 'Galen', '5554444444', 'Downtown2', 'Out for delivery'),
-- ('DP005', 'Lawrence', 'Zella', '5555555555', 'Uptown2', 'Returned'),
-- ('DP006', 'Fuller', 'Drina', '5556666666', 'Midtown2', 'Inactive'),
-- ('DP007', 'Neville', 'Trueman', '5557777777', 'Downtown3', 'Out for delivery'),
-- ('DP008', 'Beckham', 'Ardath', '5558888888', 'Uptown3', 'Returned'),
-- ('DP009', 'Harvey', 'Rupert', '5559999999', 'Midtown3', 'Inactive'),
-- ('DP010', 'Rake', 'Sparrow', '6660000000', 'Downtown4', 'Out for delivery'),
-- ('DP011', 'Browne', 'Mathilda', '6661111111', 'Uptown4', 'Returned'),
-- ('DP012', 'Cornett', 'Elicia', '6662222222', 'Midtown4', 'Inactive'),
-- ('DP013', 'Lazarus', 'Derby', '6663333333', 'Downtown5', 'Out for delivery'),
-- ('DP014', 'Candi', 'Beech', '6664444444', 'Uptown5', 'Returned'),
-- ('DP015', 'Brent', 'Peters', '6665555555', 'Midtown5', 'Inactive'),
-- ('DP016', 'Gabrielson', 'Deeann', '6666666666', 'Downtown6', 'Out for delivery'),
-- ('DP017', 'Albertson', 'Kemp', '6667777777', 'Uptown6', 'Returned'),
-- ('DP018', 'Laney', 'Rigby', '6668888888', 'Midtown6', 'Inactive'),
-- ('DP019', 'Thurstan', 'Walton', '6669999999', 'Downtown7', 'Out for delivery'),
-- ('DP020', 'Tobias', 'Hettie', '7770000000', 'Uptown7', 'Returned'),
-- ('DP021', 'Miller', 'Rosalind', '7771111111', 'Midtown7', 'Inactive'),
-- ('DP022', 'Bryant', 'Jessie', '7772222222', 'Downtown8', 'Out for delivery'),
-- ('DP023', 'Post', 'Bazza', '7773333333', 'Uptown8', 'Returned'),
-- ('DP024', 'Graves', 'Sherman', '7774444444', 'Midtown8', 'Inactive');
Select * from delivery_persons;