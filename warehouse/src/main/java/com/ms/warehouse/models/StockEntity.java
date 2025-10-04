    package com.ms.warehouse.models;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.UUID;

    @Entity
    @Table(name="TABLE_STOCK")
    @Getter
    @Setter
    public class StockEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        private String status;
        private Integer qtd;
        @OneToOne
        @JoinColumn(name = "id")
        private ProductEntity product;
    }
