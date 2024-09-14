-- Create tables
CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL
);

CREATE TABLE CustomerTransactions (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       customer_id BIGINT,
                                       spend_details VARCHAR(255),
                                       amount DECIMAL(10, 2) NOT NULL,
                                       date DATE NOT NULL,
                                       FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE CUSTOMER_POINTS (
                                 ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 CUSTOMER_ID BIGINT,
                                 REWARDMONTH INT,
                                 REWARDYEAR INT,
                                 REWARDPOINTS INT,
                                 FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(ID)
);
-- Insert dummy data
INSERT INTO customers (username, password) VALUES
                                               ('Ram', 'password123'),
                                               ('Riya', 'mypassword');

INSERT INTO CustomerTransactions (customer_id, spend_details, amount, date) VALUES
                                                                  (1, 'Shopping',120.00, '2024-01-15'),
                                                                  (1, 'Food',75.00, '2024-02-20'),
                                                                  (1, 'Food',180.00, '2024-03-20'),
                                                                  (2, 'Shopping',55.00, '2024-01-25'),
                                                                  (2, 'Hosptial', 95.00, '2024-02-05'),
                                                                  (2,'Travel',568.00,'2024-03-05');


INSERT INTO customer_points (customer_id, REWARDMONTH,REWARDYEAR,REWARDPOINTS) VALUES
                                                                   (1, 01, 2024, 90),
                                                                   (1, 02, 2024, 15),
                                                                   (1, 03, 2024, 15),
                                                                   (2, 01, 2024, 5),
                                                                   (2, 02, 2024, 45),
                                                                   (2,03,2024,418);
