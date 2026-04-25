package com.inventory.config;

import com.inventory.repository.*;
import com.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * DataSeeder — creates tables on startup and seeds demo data.
 *
 * NOTE FOR STUDENTS:
 *   This file uses JdbcTemplate directly for the seed check and seed inserts
 *   so the app can boot even before your repository TODOs are implemented.
 *   Do NOT modify this file.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        createTablesIfNotExist();
        seedDemoData();
    }

    private void seedDemoData() {
        // Use raw JdbcTemplate — does NOT depend on any student TODO
        Long productCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM products", Long.class);
        if (productCount != null && productCount > 0) {
            System.out.println("✅ Demo data already present — skipping seed.");
            return;
        }

        // ── Categories ──────────────────────────────────────
        jdbcTemplate.update(
            "INSERT INTO categories (name, description) VALUES (?, ?)",
            "Electronics", "Gadgets, devices, and accessories");
        jdbcTemplate.update(
            "INSERT INTO categories (name, description) VALUES (?, ?)",
            "Office Supplies", "Stationery, paper, and desk items");
        jdbcTemplate.update(
            "INSERT INTO categories (name, description) VALUES (?, ?)",
            "Furniture", "Desks, chairs, and shelving");

        Long elecId  = jdbcTemplate.queryForObject("SELECT id FROM categories WHERE name = 'Electronics'",  Long.class);
        Long offId   = jdbcTemplate.queryForObject("SELECT id FROM categories WHERE name = 'Office Supplies'", Long.class);
        Long furnId  = jdbcTemplate.queryForObject("SELECT id FROM categories WHERE name = 'Furniture'",    Long.class);

        // ── Suppliers ───────────────────────────────────────
        jdbcTemplate.update(
            "INSERT INTO suppliers (name, email, phone, address) VALUES (?, ?, ?, ?)",
            "TechCorp Philippines", "orders@techcorp.ph", "02-8123-4567", "Makati City, Metro Manila");
        jdbcTemplate.update(
            "INSERT INTO suppliers (name, email, phone, address) VALUES (?, ?, ?, ?)",
            "OfficePro Supply", "supply@officepro.ph", "02-8765-4321", "Quezon City, Metro Manila");

        Long techId  = jdbcTemplate.queryForObject("SELECT id FROM suppliers WHERE email = 'orders@techcorp.ph'", Long.class);
        Long offSupId = jdbcTemplate.queryForObject("SELECT id FROM suppliers WHERE email = 'supply@officepro.ph'", Long.class);

        // ── Products ────────────────────────────────────────
        String insertProduct =
            "INSERT INTO products (name, sku, description, price, stock_quantity, reorder_level, category_id, supplier_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertProduct,
            "Laptop 15\" Core i5", "ELEC-001",
            "Business laptop with 8GB RAM, 256GB SSD",
            new BigDecimal("35999.00"), 15, 5, elecId, techId);

        jdbcTemplate.update(insertProduct,
            "Wireless Mouse", "ELEC-002",
            "Ergonomic wireless mouse, 2.4GHz",
            new BigDecimal("699.00"), 8, 10, elecId, techId);

        jdbcTemplate.update(insertProduct,
            "Bond Paper A4 (500 sheets)", "OFF-001",
            "80gsm white bond paper, one ream",
            new BigDecimal("249.00"), 0, 20, offId, offSupId);

        jdbcTemplate.update(insertProduct,
            "Ballpen (Black, box of 12)", "OFF-002",
            "Standard ballpoint pen, 0.5mm",
            new BigDecimal("89.00"), 45, 15, offId, offSupId);

        jdbcTemplate.update(insertProduct,
            "Ergonomic Office Chair", "FURN-001",
            "Adjustable mesh back office chair",
            new BigDecimal("4500.00"), 3, 5, furnId, offSupId);

        System.out.println("✅ Demo data seeded: 3 categories, 2 suppliers, 5 products.");
    }

    private void createTablesIfNotExist() {
        jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS categories (" +
            "  id          BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "  name        VARCHAR(255) NOT NULL UNIQUE," +
            "  description VARCHAR(500)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS suppliers (" +
            "  id      BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "  name    VARCHAR(255) NOT NULL," +
            "  email   VARCHAR(255)," +
            "  phone   VARCHAR(50)," +
            "  address VARCHAR(500)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS products (" +
            "  id              BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "  name            VARCHAR(255) NOT NULL," +
            "  sku             VARCHAR(100) NOT NULL UNIQUE," +
            "  description     TEXT," +
            "  price           DECIMAL(10,2) NOT NULL," +
            "  stock_quantity  INT NOT NULL DEFAULT 0," +
            "  reorder_level   INT NOT NULL DEFAULT 10," +
            "  created_at      DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "  category_id     BIGINT," +
            "  supplier_id     BIGINT," +
            "  FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL," +
            "  FOREIGN KEY (supplier_id) REFERENCES suppliers(id)  ON DELETE SET NULL" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS stock_transactions (" +
            "  id               BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "  product_id       BIGINT NOT NULL," +
            "  type             ENUM('STOCK_IN','STOCK_OUT') NOT NULL," +
            "  quantity         INT NOT NULL," +
            "  reason           VARCHAR(500)," +
            "  transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        System.out.println("✅ Tables verified / created.");
    }
}
