package com.example.crud.controller;

import com.example.crud.entity.ApprovalQueue;
import com.example.crud.entity.Product;
import com.example.crud.service.ApprovalQueueService;
import com.example.crud.service.ProductService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ApprovalQueueService approvalQueueService;


    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProduct() {

        return new ResponseEntity<>(productService.fetchAllProducts(), HttpStatus.FOUND);
    }

    @GetMapping("/api/products/search")
    @ResponseBody
    public List<Product> searchProduct(@RequestParam(value = "productName", required=false) String productName,
                                       @RequestParam(value = "price", required=false,defaultValue= "0") float price,
                                       @RequestParam(value = "status", required=false) String status) {
        return productService.searchProducts(productName,price,status);

    }
    @PostMapping("/api/products")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product) {
        if(product.getPrice()<5000) {
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
        }
        ApprovalQueue approvalQueue = new ApprovalQueue();

            approvalQueue.setProductId(product.getProductId());
            approvalQueue.setProductName(product.getProductName());
            approvalQueue.setPrice(product.getPrice());
            approvalQueue.setStatus(product.getStatus());
            return new ResponseEntity<>(approvalQueueService.saveApprovalQ(approvalQueue), HttpStatus.CREATED);


    }



    @PutMapping("/api/products/{productId}")
    public Product updateProduct(@PathVariable("productId") Long id, @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }

    @DeleteMapping("/api/products/{productId}")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id)  throws ResourceNotFoundException {

        return productService.deleteProductById(id);
    }

    @GetMapping("/api/products/approval-queue")
    public ResponseEntity<List<ApprovalQueue>> getAllApprovalQ() {

        return new ResponseEntity<>(approvalQueueService.fetchAllApprovalQueue(), HttpStatus.FOUND);
    }

    @PutMapping("/api/products/approval-queue/{approvalId}/approve")
    public Product updateApprovalQApprove(@PathVariable("approvalId") Long approvalId) {
        return approvalQueueService.updateApprovalQById(approvalId);
    }

    @PutMapping("/api/products/approval-queue/{approvalId}/reject")
    public Product updateApprovalQREject(@PathVariable("approvalId") Long approvalId) {
        return approvalQueueService.updateApprovalQById(approvalId);
    }


    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable("productId") Long id) {
        return productService.getProductById(id);
    }


}
