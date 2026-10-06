CREATE DATABASE IF NOT EXISTS sales_analytics;

USE sales_analytics;

CREATE TABLE IF NOT EXISTS sales (
    sale_id INT PRIMARY KEY,
    sale_date DATE NOT NULL,
    product VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    region VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,
    unit_cost DECIMAL(12,2) NOT NULL,
    customer_type VARCHAR(30) NOT NULL,
    payment_method VARCHAR(30) NOT NULL
);