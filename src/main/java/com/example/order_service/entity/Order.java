package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;
    private String status; // CREATED, PROCESSING, FAILED
}
