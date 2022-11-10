package com.example.backend.controller;


import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Item;
import com.example.backend.entity.Order;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    JwtService jwtService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/api/orders")
    public ResponseEntity getOrder(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Order newOrder = new Order();
        newOrder.setMember_id(jwtService.getId(token));
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCard_number(dto.getCard_number());
        newOrder.setItems(dto.getItems());

        orderRepository.save(newOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
