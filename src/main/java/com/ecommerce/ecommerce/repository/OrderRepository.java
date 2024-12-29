package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT k FROM Order k WHERE k.idUser = :idUser")
    Optional<Order> findByIdUser(@Param("idUser") long idUser);


}