package com.example.demo_rabbit.reposi;

import com.example.demo_rabbit.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryOrder extends JpaRepository<Order,Integer> {
}
