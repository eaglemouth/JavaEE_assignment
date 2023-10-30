DROP DATABASE IF EXISTS userDB;

CREATE DATABASE userDB CHARACTER SET utf8 COLLATE utf8_general_ci;

USE userDB;
-- 创建商品表
CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建供货商表
CREATE TABLE supplier (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          contact_person VARCHAR(255),
                          contact_email VARCHAR(255),
                          contact_phone VARCHAR(20),
                          create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建商品和供货商关联表
CREATE TABLE product_supplier (
                                  product_id INT,
                                  supplier_id INT,
                                  PRIMARY KEY (product_id, supplier_id),
                                  FOREIGN KEY (product_id) REFERENCES product(id),
                                  FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

-- 插入示例商品数据到"product"表
INSERT INTO product (name, description, price) VALUES
                                                   ('Product A', 'Description A', 19.99),
                                                   ('Product B', 'Description B', 29.99),
                                                   ('Product C', 'Description C', 39.99);

-- 插入示例供货商数据到"supplier"表
INSERT INTO supplier (name, contact_person, contact_email, contact_phone) VALUES
                                                                              ('Supplier X', 'John Doe', 'john@example.com', '123-456-7890'),
                                                                              ('Supplier Y', 'Jane Smith', 'jane@example.com', '987-654-3210'),
                                                                              ('Supplier Z', 'Bob Johnson', 'bob@example.com', '555-123-4567');

-- 插入示例商品和供货商关联数据到"product_supplier"表
INSERT INTO product_supplier (product_id, supplier_id) VALUES
                                                           (1, 1),
                                                           (2, 2),
                                                           (3, 3),
                                                           (1, 2),
                                                           (2, 3);
