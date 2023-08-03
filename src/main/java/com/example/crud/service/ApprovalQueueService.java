package com.example.crud.service;

import com.example.crud.entity.ApprovalQueue;
import com.example.crud.entity.Product;

import java.util.List;

public interface ApprovalQueueService {

    ApprovalQueue saveApprovalQ(ApprovalQueue approvalQueue);

    List<ApprovalQueue> fetchAllApprovalQueue();

    List<ApprovalQueue> searchApprovalQueue(String productName, float price, String status);

    ApprovalQueue getApprovalQById(Long id);

    Product updateApprovalQById(Long id);

    String deleteApprovalQById(Long id);
}
