package com.example.backend.dto;

import lombok.Getter;

@Getter
public class OrderDto {

    private String name;
    private String address;
    private String payment;
    private String card_number;
    private String items;
}
