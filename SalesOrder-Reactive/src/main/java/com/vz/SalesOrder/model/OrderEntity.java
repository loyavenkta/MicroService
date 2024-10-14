package com.vz.SalesOrder.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;

    @ManyToOne //spring data jpa
    @JoinColumn(name = "sales_order_id")
    private SalesEntity salesOrder;

 
}
