package com.example.crud.repository;

import com.example.crud.entity.ApprovalQueue;
import com.example.crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalQueueRepository extends JpaRepository<ApprovalQueue, Long> {

}
