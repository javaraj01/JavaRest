package com.example.crud.service;

import com.example.crud.entity.ApprovalQueue;
import com.example.crud.entity.Product;
import com.example.crud.exception.InvalidRequestException;
import com.example.crud.exception.NoSuchProductExistException;
import com.example.crud.repository.ApprovalQueueRepository;
import com.example.crud.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApprovalQueueRepository approvalQueueRepository;

    @Override
    public Product saveProduct(Product product) {
        ApprovalQueue appQueue = new ApprovalQueue();
        if(product.getProductName()==null){
            throw new InvalidRequestException(
                    "Enter Valid Request Attribute ProductName!!");
        }
        Product product1 = productRepository.save(product);
        if(product.getPrice()>5000 && product.getPrice()<10000) {
            appQueue.setProductName(product1.getProductName());
            appQueue.setProductId(product1.getProductId());
            appQueue.setPrice(product1.getPrice());
            appQueue.setStatus("PENDING");
            ApprovalQueue approvalQueue = approvalQueueRepository.save(appQueue);
        }
        return product1;
    }

    @Override
    public List<Product> fetchAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.isEmpty()) {
            throw new NoSuchProductExistException(
                    "Product List not Available!!");
        }
        return allProducts;
    }

    @Override
    public List<Product> searchProducts(String productName, float price, String status) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> filteredProd = new ArrayList<>();
        try {
            if (allProducts == null) {
                throw new NoSuchProductExistException(
                        "Product List not Available!!");
            }

            if (productName != null) {
                List<Product> filteredName = allProducts.stream()
                        .filter(p -> p.getProductName().equalsIgnoreCase(productName) && p.getProductName() != null)
                        .collect(Collectors.toList());
                filteredProd.addAll(filteredName);
            }
            if (price != 0) {
                List<Product> filterePrice = allProducts.stream()
                        .filter(p -> p.getPrice() == (price))
                        .collect(Collectors.toList());
                filteredProd.addAll(filterePrice);
            }
            if (status != null) {
                List<Product> filtereStatus = allProducts.stream()
                        .filter(p -> p.getStatus().equalsIgnoreCase(status))
                        .collect(Collectors.toList());
                filteredProd.addAll(filtereStatus);
            }
        }catch ( NoSuchProductExistException ex){
            throw new NoSuchProductExistException(
                    "Product List not Available!!");
        }
        return filteredProd;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        Optional<Product> product1 = productRepository.findById(id);

        if (product1.isPresent()) {
            Product originalProduct = product1.get();

            if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())) {
                originalProduct.setProductName(product.getProductName());
            }
            if (Objects.nonNull(product.getPrice()) && product.getPrice() != 0) {
                originalProduct.setPrice(product.getPrice());
            }
            if (Objects.nonNull(product.getStatus()) && !"".equalsIgnoreCase(product.getStatus())) {
                originalProduct.setStatus(product.getStatus());
            }
            return productRepository.save(originalProduct);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteProductById(Long id) {
        ResponseEntity<?> responseEntity = null;
        Optional<Product> product1 = productRepository.findById(id);
        if(product1.isEmpty()){
            throw new NoSuchProductExistException(
                    "No such Product!!");
        }

        ApprovalQueue approvalQueue = new ApprovalQueue();
        try {
            if (product1.isPresent()) {
                approvalQueue.setProductId(product1.get().getProductId());
                approvalQueue.setProductName(product1.get().getProductName());
                approvalQueue.setPrice(product1.get().getPrice());
                approvalQueue.setStatus(product1.get().getStatus());
                approvalQueueRepository.save(approvalQueue);
                productRepository.deleteById(id);
                responseEntity = new ResponseEntity<String>("Product Deleted and moved this to Approval queue", HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {

        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("Unable delete product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
