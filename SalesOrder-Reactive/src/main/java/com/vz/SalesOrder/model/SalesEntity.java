package com.vz.SalesOrder.model;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data

public class SalesEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private String emailId;
    private String description;
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    private List<OrderEntity> orderLineItems = new ArrayList<>();;

    // Constructors, Getters, and Setters
}

