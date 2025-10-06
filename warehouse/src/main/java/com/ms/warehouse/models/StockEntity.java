    package com.ms.warehouse.models;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;

    import java.util.UUID;

    @Entity
    @Table(name="TABLE_STOCK")

    public class StockEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        private String status;
        private Integer qtd;
        @OneToOne
        @JoinColumn(name = "product_id")
        private ProductEntity product;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getQtd() {
            return qtd;
        }

        public void setQtd(Integer qtd) {
            this.qtd = qtd;
        }

        public ProductEntity getProduct() {
            return product;
        }

        public void setProduct(@NotBlank ProductEntity product) {
            this.product = product;
        }
    }
