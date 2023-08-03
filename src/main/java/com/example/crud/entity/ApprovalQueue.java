package com.example.crud.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Data
public class ApprovalQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long approvalQueueId;

    private Long productId;

    private String productName;

    private String status;

    @Min(value = 5000, message = "Product price must be greater or equal to 5000")
    @Max(value = 10000, message = "Product prices must be less than or equal to 10000")
    private float price;



    public ApprovalQueue() {
    }

    public ApprovalQueue(Long approvalQueueId, Long productId, String productName, float price, String status) {
        this.approvalQueueId=approvalQueueId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public ApprovalQueue(String productName, float price, String status) {
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public Long getApprovalQueueId() {
        return approvalQueueId;
    }

    public void setApprovalQueueId(Long approvalQueueId) {
        this.approvalQueueId = approvalQueueId;
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
