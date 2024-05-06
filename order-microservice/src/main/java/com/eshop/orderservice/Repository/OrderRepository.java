package com.eshop.orderservice.Repository;

import com.eshop.orderservice.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
