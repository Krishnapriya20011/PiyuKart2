package com.example.Product.repoistry;

import com.example.Product.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepoistry extends JpaRepository<Products,Integer> {
    List<Products> findByCategory(String Category); //step(4)
    List<Products> findByRateBetween(double minRate, double maxRate); //step(10)
    List<Products> findByCategoryAndRateBetween(String Category, double minRate, double maxRate); //step(11)
}
