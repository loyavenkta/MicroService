package com.vz.customerservicereactive.controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vz.customerservicereactive.model.Customer;
import com.vz.customerservicereactive.model.CustomerRepository;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class CustomerService {
	
    //@Autowired
    private final CustomerRepository customerRepository;

    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Mono<Customer> getCustomerByEmail(String email) {
        return customerRepository.findById(email);  
    }

    public Mono<Customer> addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Mono<Void> deleteCustomer(String email) {
        return customerRepository.deleteById(email);
    }
}
