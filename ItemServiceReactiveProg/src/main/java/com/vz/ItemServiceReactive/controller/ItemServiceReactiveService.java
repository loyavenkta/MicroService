package com.vz.ItemServiceReactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vz.ItemServiceReactive.model.ItemService;
import com.vz.ItemServiceReactive.model.ItemServiceRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class ItemServiceReactiveService {


	@Autowired
    private ItemServiceRepository itemServiceRepository;
  
    // Get all items (Flux for multiple items)
    public Flux<ItemService> getAllItems() {
        return itemServiceRepository.findAll();
    }

    // Get an item by ID (Mono for a single item)
    public Mono<ItemService> getItemById(Long id) {
        return itemServiceRepository.findById(id);
    }

    // Add a new item (Mono for a single item)
    public Mono<ItemService> addItem(ItemService itemService) {
        return itemServiceRepository.save(itemService);
    }

    // Update an item (Mono for a single item)
    public Mono<ItemService> updateItem(Long id, ItemService itemDetails) {
        return itemServiceRepository.findById(id)
            .flatMap(existingItem -> {
                existingItem.setItemName(itemDetails.getItemName());
                existingItem.setDetails(itemDetails.getDetails());
                existingItem.setPrice(itemDetails.getPrice());
                return itemServiceRepository.save(existingItem);
            })
            .switchIfEmpty(Mono.empty());  // Return empty Mono if item not found
    }

    // Delete an item (Mono<Void> for delete operation)
    public void deleteItem(Long id) {
        itemServiceRepository.deleteById(id);
    }

	public Mono<ItemService> getItemByItemName(String itemName) {
		return itemServiceRepository.findByItemName(itemName);
	}
}