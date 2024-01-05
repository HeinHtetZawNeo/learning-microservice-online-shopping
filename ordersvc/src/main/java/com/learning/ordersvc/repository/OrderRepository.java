package com.learning.ordersvc.repository;

import com.learning.ordersvc.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
