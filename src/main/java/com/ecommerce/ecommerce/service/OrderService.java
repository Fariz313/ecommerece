package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(long idUser, List<Long> cartIds);
    Order saveAndUpdateOrder(Order order);
    List<Order> getAllOrder(String id);
    Optional<Order> getUserOrder(long idUser);
    Order getOrderById(long id);
    Order updateOrder(Order order,long id);
    void deleteOrder(long id);
}