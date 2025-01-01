package com.ecommerce.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
    @Column(name = "payment_status", nullable = true)
    private int paymentStatus;

    @Column(name = "total", nullable = false)
    private int total;

    @ManyToOne
    @JoinColumn(name = "id_alamat", referencedColumnName = "id", insertable = true, updatable = true)
    private Alamat alamat;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    // This removes the relationship with Barang and stores static information directly in the cart.
}
