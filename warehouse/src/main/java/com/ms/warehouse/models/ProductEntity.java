package com.ms.warehouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name="TABLE_PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String status; //se esta disponivel ou nao
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private StockEntity stock;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock( StockEntity stock) {
        this.stock = stock;
    }
}
