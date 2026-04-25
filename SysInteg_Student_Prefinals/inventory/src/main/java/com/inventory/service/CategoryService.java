package com.inventory.service;

import com.inventory.model.Category;
import com.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ┌─────────────────────────────────────────────────────────────────┐
 * │  GROUP 1 — CategoryService                                      │
 * │  Wire the repository to the controller layer.                   │
 * │  Each method delegates to CategoryRepository.                   │
 * │                                                                 │
 * │  Pattern: call the matching repository method and return        │
 * │  its result. No extra logic needed unless stated.               │
 * └─────────────────────────────────────────────────────────────────┘
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // ── EXAMPLE ─────────────────────────────────────────────────────────────
    // Fetch all categories — delegates directly to the repository.
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ── TODO 1 ──────────────────────────────────────────────────────────────
    // Return a single category wrapped in Optional (it may not exist).
    public Optional<Category> getCategoryById(Long id) {
        // TODO: return categoryRepository.findById(id)
        throw new UnsupportedOperationException("TODO 1 — getCategoryById not implemented yet");
    }

    // ── TODO 2 ──────────────────────────────────────────────────────────────
    // Save (insert or update) a category and return the saved object.
    public Category saveCategory(Category category) {
        // TODO: return categoryRepository.save(category)
        throw new UnsupportedOperationException("TODO 2 — saveCategory not implemented yet");
    }

    // ── TODO 3 ──────────────────────────────────────────────────────────────
    // Delete a category by id.
    public void deleteCategory(Long id) {
        // TODO: call categoryRepository.deleteById(id)
        throw new UnsupportedOperationException("TODO 3 — deleteCategory not implemented yet");
    }

    // ── TODO 4 ──────────────────────────────────────────────────────────────
    // Return true if the given category name is already taken.
    public boolean isNameTaken(String name) {
        // TODO: return categoryRepository.existsByName(name)
        throw new UnsupportedOperationException("TODO 4 — isNameTaken not implemented yet");
    }
}
