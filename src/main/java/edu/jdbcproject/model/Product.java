package edu.jdbcproject.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
}
