package com.example.Order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class OrderEureka {

    @Id
    private String id;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String status; // CREATED, CANCELLED, COMPLETED

    private List<OrderItem> items;

    public OrderEureka() {}

    public OrderEureka(String status, List<OrderItem> items) {
        this.status = status;
        this.items = items;
    }

    // Getters and setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // Nested class
    public static class OrderItem {
        private Long productId;
        private String sku;
        private String name;
        private int quantity;
        private double priceAtPurchase;
        private double lineTotal;

        public OrderItem() {}

        public OrderItem(Long productId, String sku, String name, int quantity, double priceAtPurchase) {
            this.productId = productId;
            this.sku = sku;
            this.name = name;
            this.quantity = quantity;
            this.priceAtPurchase = priceAtPurchase;
            this.lineTotal = quantity * priceAtPurchase;
        }

        // Getters and setters
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public String getSku() { return sku; }
        public void setSku(String sku) { this.sku = sku; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getPriceAtPurchase() { return priceAtPurchase; }
        public void setPriceAtPurchase(double priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }

        public double getLineTotal() { return lineTotal; }
        public void setLineTotal(double lineTotal) { this.lineTotal = lineTotal; }
    }
}
