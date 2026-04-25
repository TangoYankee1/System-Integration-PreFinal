package com.inventory.repository;

import com.inventory.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * ┌─────────────────────────────────────────────────────────────────┐
 * │  GROUP 2 — SupplierRepository                                   │
 * │  Complete every TODO below using JdbcTemplate.                  │
 * │                                                                 │
 * │  TABLE: suppliers                                               │
 * │    id      BIGINT  (PK, auto-increment)                         │
 * │    name    VARCHAR                                              │
 * │    email   VARCHAR                                              │
 * │    phone   VARCHAR                                              │
 * │    address VARCHAR                                              │
 * │                                                                 │
 * │  USEFUL JdbcTemplate METHODS                                    │
 * │  ─────────────────────────────────────────────────────────────  │
 * │  jdbcTemplate.query(sql, rowMapper)           → List<T>         │
 * │  jdbcTemplate.query(sql, rowMapper, arg)      → List<T>         │
 * │  jdbcTemplate.queryForObject(sql, Long.class) → Long            │
 * │  jdbcTemplate.update(sql, args...)            → int             │
 * └─────────────────────────────────────────────────────────────────┘
 */
@Repository
public class SupplierRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * RowMapper — converts one ResultSet row → Supplier object.
     * Already provided. Study how it maps column names to setters.
     */
    private final RowMapper<Supplier> rowMapper = (rs, rowNum) -> {
        Supplier s = new Supplier();
        s.setId(rs.getLong("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setAddress(rs.getString("address"));
        return s;
    };

    // ── EXAMPLE ─────────────────────────────────────────────────────────────
    // Returns all suppliers ordered A → Z by name.
    public List<Supplier> findAll() {
        String sql = "SELECT id, name, email, phone, address FROM suppliers ORDER BY name ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // ── TODO 1 ──────────────────────────────────────────────────────────────
    // Find ONE supplier by its id. Return Optional.empty() if not found.
    public Optional<Supplier> findById(Long id) {
        // TODO: SELECT ... WHERE id = ?  then wrap in Optional
        throw new UnsupportedOperationException("TODO 1 — findById not implemented yet");
    }

    // ── TODO 2 ──────────────────────────────────────────────────────────────
    // Find ONE supplier by their exact email address.
    public Optional<Supplier> findByEmail(String email) {
        // TODO: SELECT ... WHERE email = ?
        throw new UnsupportedOperationException("TODO 2 — findByEmail not implemented yet");
    }

    // ── TODO 3 ──────────────────────────────────────────────────────────────
    // Return true if a supplier with the given email already exists.
    public boolean existsByEmail(String email) {
        // TODO: SELECT COUNT(*) FROM suppliers WHERE email = ?
        throw new UnsupportedOperationException("TODO 3 — existsByEmail not implemented yet");
    }

    // ── TODO 4 ──────────────────────────────────────────────────────────────
    // Find all suppliers whose name contains the keyword (case-insensitive).
    public List<Supplier> searchByKeyword(String keyword) {
        // TODO: LOWER(name) LIKE LOWER(?) — wrap keyword with %...%
        throw new UnsupportedOperationException("TODO 4 — searchByKeyword not implemented yet");
    }

    // ── TODO 5 ──────────────────────────────────────────────────────────────
    // Save a supplier: INSERT if id is null, UPDATE if id exists.
    // The INSERT block is shown — complete the UPDATE block.
    public Supplier save(Supplier supplier) {
        if (supplier.getId() == null) {
            // ── INSERT (provided as example) ─────────────────────────────
            String sql = "INSERT INTO suppliers (name, email, phone, address) VALUES (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, supplier.getName());
                ps.setString(2, supplier.getEmail());
                ps.setString(3, supplier.getPhone());
                ps.setString(4, supplier.getAddress());
                return ps;
            }, keyHolder);
            supplier.setId(keyHolder.getKey().longValue());
        } else {
            // ── TODO: UPDATE ─────────────────────────────────────────────
            // Columns to update: name, email, phone, address — filter by id
            throw new UnsupportedOperationException("TODO 5 — UPDATE in save() not implemented yet");
        }
        return supplier;
    }

    // ── TODO 6 ──────────────────────────────────────────────────────────────
    // Delete a supplier by its id.
    public void deleteById(Long id) {
        // TODO: DELETE FROM suppliers WHERE id = ?
        throw new UnsupportedOperationException("TODO 6 — deleteById not implemented yet");
    }

    // ── TODO 7 (BONUS) ──────────────────────────────────────────────────────
    // Return the total count of all suppliers.
    public long count() {
        // TODO: SELECT COUNT(*) FROM suppliers
        throw new UnsupportedOperationException("TODO 7 — count not implemented yet");
    }
}
