package com.example.crud.service;

import com.example.crud.entity.ApprovalQueue;
import com.example.crud.entity.Product;
import com.example.crud.repository.ApprovalQueueRepository;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprovalQaueServiceImpl implements ApprovalQueueService {

    @Autowired
    private ApprovalQueueRepository approvalQueueRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ApprovalQueue saveApprovalQ(ApprovalQueue approvalQueue) {

        return approvalQueueRepository.save(approvalQueue);
    }

    @Override
    public List<ApprovalQueue> fetchAllApprovalQueue() {
        List<ApprovalQueue> allApprovalQueue = approvalQueueRepository.findAll();
        return allApprovalQueue;
    }

    @Override
    public List<ApprovalQueue> searchApprovalQueue(String productName, float price, String status) {
        List<ApprovalQueue> allAppQueues = approvalQueueRepository.findAll();
        List<ApprovalQueue> filteredAppQ = new ArrayList<>();
        if(productName!=null) {
            List<ApprovalQueue> filteredName = allAppQueues.stream()
                    .filter(p -> p.getProductName().equalsIgnoreCase(productName))
                    .collect(Collectors.toList());
            filteredAppQ.addAll(filteredName);
        }
        List<ApprovalQueue> filterePrice = allAppQueues.stream()
                .filter(p -> p.getPrice()==(price))
                .collect(Collectors.toList());
        filteredAppQ.addAll(filterePrice);
        if(status!=null) {
            List<ApprovalQueue> filtereStatus = allAppQueues.stream()
                    .filter(p -> p.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
            filteredAppQ.addAll(filtereStatus);
        }
        return filteredAppQ;
    }
    @Override
    public ApprovalQueue getApprovalQById(Long id) {
        Optional<ApprovalQueue> approvalQueue = approvalQueueRepository.findById(id);
        if (approvalQueue.isPresent()) {
            return approvalQueue.get();
        }
        return null;
    }

    public Product updateApprovalQById(Long id) {
        Optional<ApprovalQueue> approvalQ1 = approvalQueueRepository.findById(id);
        Product originalProd=null;
        if (approvalQ1.isPresent()) {

            Optional<Product> product = productRepository.findById(approvalQ1.get().getProductId());
            originalProd =product.get();
            originalProd.setStatus("APPROVED");
            productRepository.save(originalProd);
            approvalQueueRepository.deleteById(approvalQ1.get().getApprovalQueueId());
        }
        return originalProd;
    }

    @Override
    public String deleteApprovalQById(Long id) {
        if (approvalQueueRepository.findById(id).isPresent()) {
            approvalQueueRepository.deleteById(id);
            return "Apparoval Queue deleted successfully";
        }
        return "No such ApprovalQueue in the database";
    }
}
