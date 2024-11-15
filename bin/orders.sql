USE newsagent;

DROP TABLE IF EXISTS orders;

-- Create orders table
CREATE TABLE orders (
    order_id VARCHAR(50) PRIMARY KEY, 
    cust_id INT NOT NULL, 
    delivery_id VARCHAR(50) NOT NULL, 
    publication_id VARCHAR(20) NOT NULL, 
    order_date DATE DEFAULT CURRENT_DATE, 
    order_status VARCHAR(20) NOT NULL 
--     FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
--     FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE
  -- FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
    ); 


INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES
('ORD0001', 1, 'AREA01', 'PUB001', NOW(), 'PENDING'),  -- New order with status PENDING
('ORD0002', 2, 'AREA02', 'PUB002', NOW(), 'CONFIRMED'),  -- Order confirmed
('ORD0003', 3, 'AREA03', 'PUB003', NOW(), 'DISPATCHED');  -- Order dispatched

Select *  from orders;
-- Select orders from orders WHERE order_id = ORD0001;