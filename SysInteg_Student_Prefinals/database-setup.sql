-- =============================================
--  Inventory MS — MySQL Setup Script
--  Run this ONCE before starting the app.
--  NOTE: Tables are also auto-created by
--        DataSeeder.java on first startup.
-- =============================================

CREATE DATABASE IF NOT EXISTS inventorydb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE inventorydb;

-- categories
CREATE TABLE IF NOT EXISTS categories (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(500)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- suppliers
CREATE TABLE IF NOT EXISTS suppliers (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255),
    phone   VARCHAR(50),
    address VARCHAR(500)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- products
CREATE TABLE IF NOT EXISTS products (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    sku            VARCHAR(100) NOT NULL UNIQUE,
    description    TEXT,
    price          DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    reorder_level  INT NOT NULL DEFAULT 10,
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    category_id    BIGINT,
    supplier_id    BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)  ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- stock_transactions
CREATE TABLE IF NOT EXISTS stock_transactions (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id       BIGINT NOT NULL,
    type             ENUM('STOCK_IN','STOCK_OUT') NOT NULL,
    quantity         INT NOT NULL,
    reason           VARCHAR(500),
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- To reset everything:
-- DROP DATABASE inventorydb;
-- Then re-run this script.
