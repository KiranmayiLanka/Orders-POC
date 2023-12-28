package com.OrderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApi.entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
