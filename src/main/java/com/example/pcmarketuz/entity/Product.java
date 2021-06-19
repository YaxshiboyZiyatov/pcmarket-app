package com.example.pcmarketuz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Category category;

    private boolean active = true;

    @ManyToOne
    private Attachment attachment;
}
