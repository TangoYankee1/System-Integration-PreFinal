package com.inventory.service;

import com.inventory.model.Supplier;
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ┌─────────────────────────────────────────────────────────────────┐
 * │  GROUP 2 — SupplierService                                      │
 * │  Wire the repository to the controller layer.                   │
 * └─────────────────────────────────────────────────────────────────┘
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // ── EXAMPLE ─────────────────────────────────────────────────────────────
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // ── TODO 1 ──────────────────────────────────────────────────────────────
    public Optional<Supplier> getSupplierById(Long id) {
        // TODO: return supplierRepository.findById(id)
        throw new UnsupportedOperationException("TODO 1 — getSupplierById not implemented yet");
    }

    // ── TODO 2 ──────────────────────────────────────────────────────────────
    public Supplier saveSupplier(Supplier supplier) {
        // TODO: return supplierRepository.save(supplier)
        throw new UnsupportedOperationException("TODO 2 — saveSupplier not implemented yet");
    }

    // ── TODO 3 ──────────────────────────────────────────────────────────────
    public void deleteSupplier(Long id) {
        // TODO: call supplierRepository.deleteById(id)
        throw new UnsupportedOperationException("TODO 3 — deleteSupplier not implemented yet");
    }

    // ── TODO 4 ──────────────────────────────────────────────────────────────
    // Return true if the given email is already registered to another supplier.
    public boolean isEmailTaken(String email) {
        // TODO: return supplierRepository.existsByEmail(email)
        throw new UnsupportedOperationException("TODO 4 — isEmailTaken not implemented yet");
    }
}
