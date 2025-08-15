package com.example.Order.service;

import com.example.Order.client.ProductClient;
import com.example.Order.dto.ProductDTO;
import com.example.Order.model.OrderEureka;
import com.example.Order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public OrderEureka createOrder(OrderEureka orderEureka) {
        // Reserve stock for each item
        for (OrderEureka.OrderItem item : orderEureka.getItems()) {
            productClient.reserveStock(item.getProductId(), item.getQuantity());
            ProductDTO productDTO =  productClient.getProductById(item.getProductId());
            item.setName(productDTO.getName());
            item.setSku(productDTO.getSku());
            item.setPriceAtPurchase(productDTO.getPrice());
            item.setLineTotal(item.getQuantity() * item.getPriceAtPurchase());
        }

        orderEureka.setStatus("CREATED");
        return orderRepository.save(orderEureka);
    }

    public Optional<OrderEureka> cancelOrder(String id) {
        return orderRepository.findById(id).map(orderEureka -> {
            // Release stock for each item
            for (OrderEureka.OrderItem item : orderEureka.getItems()) {
                productClient.releaseStock(item.getProductId(), item.getQuantity());
            }
            orderEureka.setStatus("CANCELLED");
            return orderRepository.save(orderEureka);
        });
    }

//    public Order createOrder(Order order) {
//        order.setStatus("CREATED");
//        return orderRepository.save(order);
//    }

    public List<OrderEureka> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderEureka> getOrderById(String id) {
        return orderRepository.findById(id);
    }

//    public Optional<Order> cancelOrder(String id) {
//        return orderRepository.findById(id).map(order -> {
//            order.setStatus("CANCELLED");
//            return orderRepository.save(order);
//        });
//    }
}
