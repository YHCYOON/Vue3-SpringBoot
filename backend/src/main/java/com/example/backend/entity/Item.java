package com.example.backend.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가값
    private int id;
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String img_path;

    @Column
    private int price;

    @Column
    private int discountPer;
}
