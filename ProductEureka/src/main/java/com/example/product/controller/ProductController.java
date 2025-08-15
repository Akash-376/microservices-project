package com.example.product.controller;

import com.example.product.model.ProductEureka;
import com.example.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEureka> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEureka> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductEureka createProduct(@RequestBody ProductEureka productEureka) {
        return productService.createProduct(productEureka);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEureka> updateProduct(@PathVariable Long id, @RequestBody ProductEureka productEureka) {
        return productService.updateProduct(id, productEureka)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveStock(@PathVariable Long id, @RequestParam int qty) {
        return productService.getProductById(id)
                .map(productEureka -> {
                    if (productEureka.getStock() >= qty) {
                        productEureka.setStock(productEureka.getStock() - qty);
                        productService.updateProduct(id, productEureka);
                        return ResponseEntity.ok("Stock reserved");
                    } else {
                        return ResponseEntity.badRequest().body("Insufficient stock");
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/release")
    public ResponseEntity<String> releaseStock(@PathVariable Long id, @RequestParam int qty) {
        return productService.getProductById(id)
                .map(productEureka -> {
                    productEureka.setStock(productEureka.getStock() + qty);
                    productService.updateProduct(id, productEureka);
                    return ResponseEntity.ok("Stock released");
                }).orElse(ResponseEntity.notFound().build());
    }

}
