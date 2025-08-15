package com.example.Order.client;

import com.example.Order.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PostMapping("/products/{id}/reserve")
    void reserveStock(@PathVariable("id") Long id, @RequestParam("qty") int quantity);

    @PostMapping("/products/{id}/release")
    void releaseStock(@PathVariable("id") Long id, @RequestParam("qty") int quantity);
}
