package com.example.Product;
import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer sNo;
    @Column
    Integer cartId;
    @OneToOne
    Products products;
    @OneToOne
    Customers customers;
    @Column
    int quantity;
}

