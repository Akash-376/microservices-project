package com.example.Order.repository;

import com.example.Order.model.OrderEureka;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEureka, String> {
}
