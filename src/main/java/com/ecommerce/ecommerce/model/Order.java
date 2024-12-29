package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user", nullable = false)
    private long idUser;
    
    @Column(name = "id_alamat", nullable = true)
    private int idAlamat;

    @Column(name = "payment_status", nullable = true)
    private int paymentStatus;

    @Column(name = "total", nullable = false)
    private int total;

    // This removes the relationship with Barang and stores static information directly in the cart.
}
