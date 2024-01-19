package com.lygin.beekeepersshopapi.repositories;

import com.lygin.beekeepersshopapi.entity.Order;
import com.lygin.beekeepersshopapi.entity.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
