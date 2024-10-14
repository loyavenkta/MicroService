package com.vz.SalesOrder.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vz.SalesOrder.model.OrderDto;
import com.vz.SalesOrder.model.SalesEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/service3")
@RequiredArgsConstructor
public class SalesOrderController {
   @Autowired
    private SalesOrderService salesOrderService;

    @PostMapping("/orders")
    public Mono<Long> createOrder(@RequestBody OrderDto orderDto){
        return salesOrderService.createOrder(orderDto.getDescription(), orderDto.getOrderDate(), orderDto.getEmailId(), orderDto.getItemNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesEntity> getOrderById(@PathVariable Long id) {
        Optional<SalesEntity> order = salesOrderService.getOrder(id).blockOptional();
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

