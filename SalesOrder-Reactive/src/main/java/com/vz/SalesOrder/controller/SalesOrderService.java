package com.vz.SalesOrder.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.vz.SalesOrder.model.OrderEntity;
import com.vz.SalesOrder.model.SalesEntity;
import com.vz.SalesOrder.model.SalesRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SalesOrderService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private WebClient.Builder webClientBuilder; // WebClient for reactive HTTP calls

    private Long id;

    public Mono<Long> createOrder(String description, LocalDate orderDate, String emailId, List<String> itemNames) {
        WebClient webClient = webClientBuilder.build();

        // Validate customer (non-blocking)
        String customerValidationUrl = "http://localhost:8086/api/customers/" + emailId;
        Mono<Boolean> customerExistsMono = webClient.get()
            .uri(customerValidationUrl)
            .retrieve()
            .bodyToMono(Object.class)
            .hasElement();
        if(!customerExistsMono.block()) {}
        	
        // Calculate total price and validate items (non-blocking)
        Flux<BigDecimal> itemPricesFlux = Flux.fromIterable(itemNames)
            .flatMap(itemName -> {
                String itemValidationUrl = "http://localhost:8087/api/items/name/" + itemName;
                return webClient.get()
                    .uri(itemValidationUrl)
                    .retrieve()
                    .bodyToMono(BigDecimal.class)
                    .flatMap(itemPrice -> {
                        if (itemPrice == null) {
                            return Mono.error(new RuntimeException("Invalid item: " + itemName));
                        }
                        return Mono.just(itemPrice);
                    });
            });

        Mono<BigDecimal> totalPriceMono = itemPricesFlux
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return customerExistsMono
            .then(totalPriceMono)
            .flatMap(totalPrice -> {
                // Create and Save Order
                SalesEntity salesOrder = new SalesEntity();
                salesOrder.setDescription(description);
                salesOrder.setOrderDate(orderDate);
                salesOrder.setEmailId(emailId);
                salesOrder.setTotalPrice(totalPrice);

                // Add Order Line Items
                itemNames.forEach(itemName -> {
                    OrderEntity order = new OrderEntity();
                    order.setItemName(itemName);
                    order.setQuantity(1);  // Example: setting quantity to 1 for simplicity
                    order.setSalesOrder(salesOrder);
                    salesOrder.getOrderLineItems().add(order);
                });

                // Save Order to Database (blocking operation wrapped in Mono)
                return Mono.fromCallable(() -> salesRepository.save(salesOrder))
                    //.map(SalesEntity::getId);
                    .flatMap(mono -> mono.map(SalesEntity::getId));

            });
    }	

    public Mono<SalesEntity> getOrder(Long id) {
        return Mono.fromCallable(() -> salesRepository.findById(id))
            .flatMap(optionalSalesOrder -> optionalSalesOrder
                .map(Mono::just)
                .flatMap(mono -> mono.switchIfEmpty(Mono.empty())));
            // .orElse(Mono.empty())
    }
}
