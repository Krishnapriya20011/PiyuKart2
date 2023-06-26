package com.example.Product;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="orderproducts")
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer orderId;
    @Column
    int cartId;
    @Column
    int quantity;
    @Column
    String productname;
    @Column
    double totalamount;
    @Column
    LocalDate orderDate;
    @Column
    LocalDate deliveryDate;

}
