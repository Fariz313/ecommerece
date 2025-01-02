package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.OrderRequest;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.service.OrderService;
import com.midtrans.Midtrans;
import com.midtrans.httpclient.SnapApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Memanggil layanan untuk menyimpan pesanan
            Order order = orderService.saveOrder(orderRequest.getIdUser(), orderRequest.getCartIds());

            // Mengembalikan respons sukses
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            // Menangani error jika ID keranjang tidak valid
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Menangani error lainnya
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the order.");
        }
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.saveAndUpdateOrder(order), HttpStatus.CREATED);
    }

    // GetAll Rest Api
    @GetMapping
    public List<Order> getAllOrder(@RequestParam(required = false) String id) {
        return orderService.getAllOrder(id);
    }

    // Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/orders/1
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long orderID) {
        return new ResponseEntity<Order>(orderService.getOrderById(orderID), HttpStatus.OK);
    }

    // Get by User_Id Rest Api
    @GetMapping("{id}/user")
    // localhost:8080/api/orders/1
    public ResponseEntity<?> getOrderByUserId(@PathVariable("id") long orderID) {
        return new ResponseEntity<Optional<Order>>(orderService.getUserOrder(orderID), HttpStatus.OK);
    }

    // Update Rest Api
    @PostMapping("/edit/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") long id,
            @RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.updateOrder(order, id), HttpStatus.OK);
    }

    // Delete Rest Api
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
        // delete order from db
        orderService.deleteOrder(id);
        return new ResponseEntity<String>("Order deleted Successfully.", HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<Optional<Order>> getOrderByUser(@PathVariable long idUser) {
        Optional<Order> order = orderService.getUserOrder(idUser);
        return ResponseEntity.ok(order);
    }

    public Map<String, Object> requestBody(String total) {
        UUID idRand = UUID.randomUUID();
        Map<String, Object> params = new HashMap<>();

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", idRand.toString());
        transactionDetails.put("gross_amount", total);

        Map<String, String> creditCard = new HashMap<>();
        creditCard.put("secure", "true");

        params.put("transaction_details", transactionDetails);
        params.put("credit_card", creditCard);

        return params;
    }
    
    @PostMapping("/payment")
    public ResponseEntity<?> createTransaction(@RequestBody String orderId,@RequestParam(required = false) String total) {
        try {
            Midtrans.serverKey ="SB-Mid-server-8gP0RWfsr-BkNR5MRCwa1Ocy";
            Midtrans.isProduction = false;
            String transactionToken = SnapApi.createTransactionToken(requestBody(total));
            return ResponseEntity.ok(transactionToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}