package com.example.Product.repoistry;

import com.example.Product.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepoistry extends JpaRepository<OrderProducts, Integer> {
}
