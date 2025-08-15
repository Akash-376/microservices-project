package com.example.product.service;

import com.example.product.model.ProductEureka;
import com.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEureka> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEureka> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEureka createProduct(ProductEureka productEureka) {
        return productRepository.save(productEureka);
    }

    public Optional<ProductEureka> updateProduct(Long id, ProductEureka updatedProductEureka) {
        return productRepository.findById(id).map(productEureka -> {
            productEureka.setName(updatedProductEureka.getName());
            productEureka.setSku(updatedProductEureka.getSku());
            productEureka.setPrice(updatedProductEureka.getPrice());
            productEureka.setStock(updatedProductEureka.getStock());
            return productRepository.save(productEureka);
        });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(productEureka -> {
            productRepository.delete(productEureka);
            return true;
        }).orElse(false);
    }
}
