package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Alamat;
import com.ecommerce.ecommerce.model.Keranjang;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.repository.AlamatRepository;
import com.ecommerce.ecommerce.repository.KeranjangRepository;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.OrderService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AlamatRepository alamatRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private KeranjangRepository keranjangRepository;

    // save order in database
    @Override
    public Order saveAndUpdateOrder(Order order) {
        Optional<Order> existingOrder = orderRepository
                .findByIdUser(order.getIdUser());

        if (existingOrder.isPresent()) {
            Order orderToUpdate = existingOrder.get();
            // orderToUpdate.setJumlah(orderToUpdate.getJumlah() + 1);
            return orderRepository.save(orderToUpdate);
        } else {
            return orderRepository.save(order);
        }
    }

    // get all order form database
    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getUserOrder(long idUser) {
        return orderRepository.findByIdUser(idUser);
    }

    // get order using id
    @Override
    public Order getOrderById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update order
    @Override
    public Order updateOrder(Order order, long id) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException());
        if (order.getAlamat() != null && order.getAlamat().getId() > 0) {
            Alamat alamat = alamatRepository.findById(order.getAlamat().getId())
                    .orElseThrow(() -> new RuntimeException("Alamat not found"));
            existingOrder.setAlamat(alamat);
        }
        existingOrder.setPaymentStatus(order.getPaymentStatus());
        existingOrder.setTotal(order.getTotal());
        // save
        orderRepository.save(existingOrder);
        return existingOrder;
    }

    // delete order
    @Override
    public void deleteOrder(long id) {
        // check
        orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Order saveOrder(long idUser, List<Long> cartIds) {
        // Fetch all cart items by IDs
        List<Keranjang> keranjangList = keranjangRepository.findAllById(cartIds);

        if (keranjangList.isEmpty()) {
            throw new IllegalArgumentException("No items found for the provided cart IDs.");
        }

        // Calculate total amount
        int totalAmount = keranjangList.stream()
                .mapToInt(keranjang -> Integer.parseInt(keranjang.getBarang().getHarga()) * keranjang.getJumlah())
                .sum();

        // Save Order
        Order order = new Order();
        order.setIdUser(idUser);
        order.setTotal(totalAmount);
        Order savedOrder = orderRepository.save(order);

        // Save Order Items
        List<OrderItem> orderItems = keranjangList.stream().map(keranjang -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setIdBarang(keranjang.getIdBarang());
            orderItem.setBarangNama(keranjang.getBarang().getNama());
            orderItem.setBarangHarga(Double.parseDouble(keranjang.getBarang().getHarga()));
            orderItem.setBarangGambar(keranjang.getBarang().getGambar());
            orderItem.setJumlah(keranjang.getJumlah());
            return orderItem;
        }).toList();

        orderItemRepository.saveAll(orderItems);

        // Optional: Clear cart items after saving order
        keranjangRepository.deleteAll(keranjangList);

        return savedOrder;
    }

}