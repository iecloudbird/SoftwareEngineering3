USE newsagent;
-- Newsagent
DROP TABLE IF EXISTS newsagent;

-- Create newsagent table
CREATE TABLE newsagent (
	agent_name VARCHAR(50) PRIMARY KEY,
    agent_address VARCHAR(50) NOT NULL,
    agent_phone VARCHAR(50) NOT NULL,
    agent_email VARCHAR(50) NOT NULL
    -- FOREIGN KEY (cust_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
--     FOREIGN KEY (delivery_id) REFERENCES delivery_persons(delivery_person_id) ON DELETE CASCADE,
--     FOREIGN KEY (publication_id) REFERENCES publications(publication_id) ON DELETE CASCADE
);

INSERT INTO newsagent (agent_name, agent_address, agent_phone, agent_email) VALUES
('Mike', 'Athlone', '0871234567', 'mike@newsagent.com');
Select * from newsagent;