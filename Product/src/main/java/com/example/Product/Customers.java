package com.example.Product;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customerId;
    @Column
    String customerName;
    @Column
    String email;
    @Column
    String phoneNumber;
}
