CREATE TABLE IF NOT EXISTS Item_Service (
    id BIGINT PRIMARY KEY,
    item_name VARCHAR(255),
    details VARCHAR(255),
    price DECIMAL(10, 2)
);

INSERT INTO Item_Service (id,item_name, details, price) 
VALUES (1, 'Laptop', 'High-performance laptop with 16GB RAM and 512GB SSD', 1200.00),
(2, 'Smartphone', 'Latest model smartphone with OLED display', 799.99);