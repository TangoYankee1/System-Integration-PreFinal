package com.inventory.model;

import java.time.LocalDateTime;

/**
 * Plain Java class — no JPA/Hibernate annotations.
 * Maps to the 'stock_transactions' table in MySQL.
 */
public class StockTransaction {

    public enum Type { STOCK_IN, STOCK_OUT }

    private Long id;
    private Product product;
    private Type type;
    private Integer quantity;
    private String reason;
    private LocalDateTime transactionDate = LocalDateTime.now();

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Type getType() { return type; }
    public Integer getQuantity() { return quantity; }
    public String getReason() { return reason; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    public void setId(Long id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setType(Type type) { this.type = type; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setReason(String reason) { this.reason = reason; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
