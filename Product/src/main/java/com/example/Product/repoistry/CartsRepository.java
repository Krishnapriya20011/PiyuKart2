package com.example.Product.repoistry;

import com.example.Product.Cart;
import com.example.Product.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartsRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByCartId(Integer cartId);
}
