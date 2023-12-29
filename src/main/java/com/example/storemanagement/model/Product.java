package com.example.storemanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique=true)
    private String name;

    private Double price;
    private String description;

}
