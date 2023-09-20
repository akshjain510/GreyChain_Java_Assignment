DROP TABLE IF EXISTS customer_details;
DROP TABLE IF EXISTS lender_details;
DROP TABLE IF EXISTS loan_details;


CREATE TABLE customer_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME,
    updated_at DATETIME,
    full_name VARCHAR(255),
    dob DATE,
    income_type VARCHAR(255)
);

INSERT INTO customer_details (full_name, dob, income_type)
VALUES
('Customer 1', '1995-03-20', 'SALARIED'),
('Customer 2', '1988-11-10', 'STUDENT'),
('Customer 3', '1992-07-05', 'SALARIED');


CREATE TABLE lender_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME,
    updated_at DATETIME,
    full_name VARCHAR(255),
    dob DATE,
    risk_profile VARCHAR(255)
);

INSERT INTO lender_details (full_name, dob, risk_profile)
VALUES
('Lender 1', '1990-05-15', 'LOW'),
('Lender 2', '1985-09-25', 'HIGH'),
('Lender 3', '1988-03-10', 'LOW');


CREATE TABLE loan_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME,
    updated_at DATETIME,
    customer_id BIGINT,
    lender_id BIGINT,
    amount DOUBLE,
    remaining_amount DOUBLE,
    payment_date DATE,
    interest_per_day_in_percent FLOAT,
    due_date DATE,
    penalty_per_day_in_percent FLOAT,
    cancel TINYINT
);

INSERT INTO loan_details (customer_id, lender_id, amount, remaining_amount, payment_date, interest_per_day_in_percent, due_date, penalty_per_day_in_percent, cancel)
VALUES
(1, 1, 1500.00, 1400.00, '2022-09-20', 2.5, '2022-10-20', 0.5, false),
(2, 2, 2000.00, 1900.00, '2023-09-21', 3.0, '2023-10-21', 0.6, false),
(3, 2, 1800.00, 1750.00, '2023-09-22', 2.0, '2023-10-22', 0.4, true),
(2, 3, 2200.00, 1950.00, '2023-09-23', 2.8, '2023-10-23', 0.3, false),
(1, 1, 2600.00, 2400.00, '2023-09-24', 3.2, '2023-10-24', 0.7, false),
(1, 1, 1950.00, 1800.00, '2023-09-25', 2.2, '2023-10-25', 0.4, false);

