package com.example.crud.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;

    private String status;

    @Min(value = 5000, message = "Product price must be greater or equal to 5000")
    @Max(value = 10000, message = "Product prices must be less than or equal to 10000")
    private float price;



    public Product() {
    }

    public Product(Long productId, String productName, float price, String status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public Product(String productName, float price, String status) {
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
