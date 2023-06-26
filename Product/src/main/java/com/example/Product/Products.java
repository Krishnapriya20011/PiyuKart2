package com.example.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.IntStream;
@Data
@Entity
@Table (name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer productId;
    @Column
    String name;
    @Column
    String category;
    @Column
    double rate;
    @Column
    int stock;
    @Column
    String regionAvailability;
    @Column
    String description;
    @Column
    float ratings;
    @Column
    int reviewCount;
}
