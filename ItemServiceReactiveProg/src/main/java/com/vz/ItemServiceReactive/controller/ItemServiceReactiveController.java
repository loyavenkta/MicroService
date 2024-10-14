package com.vz.ItemServiceReactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vz.ItemServiceReactive.model.ItemService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor

public class ItemServiceReactiveController {
	 // Get all items

  
    private final ItemServiceReactiveService itemService;

    // Get all items
    @GetMapping
    public Flux<ItemService> getAllItems() {
        return itemService.getAllItems();
    }

    // Get an item by ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ItemService>> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{itemName}")
    public Mono<ItemService> getItemByItemName(@PathVariable String itemName) {
        return itemService.getItemByItemName(itemName);
    }

    // Add a new item
    @PostMapping
    public Mono<ItemService> addItem(@RequestBody ItemService itemServiceRequest) {
        return itemService.addItem(itemServiceRequest);
    }

    // Update an existing item
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ItemService>> updateItem(@PathVariable Long id, @RequestBody ItemService itemDetails) {
        return itemService.updateItem(id, itemDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Delete an item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
         itemService.deleteItem(id);
    }
}