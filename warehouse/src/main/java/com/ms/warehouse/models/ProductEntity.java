package com.ms.warehouse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="TABLE_PRODUCT")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String status; //se esta disponivel ou nao
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private StockEntity stock;
}
