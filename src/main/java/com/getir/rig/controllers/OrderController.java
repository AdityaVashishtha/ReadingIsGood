package com.getir.rig.controllers;

import com.getir.rig.entities.BookOrder;
import com.getir.rig.models.CreateOrderModel;
import com.getir.rig.services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.getir.rig.config.ApplicationConstant.ORDER_PATH;

@RestController
@RequestMapping(ORDER_PATH)
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<BookOrder> newOrder(@RequestBody CreateOrderModel createOrderModel) {
        return new ResponseEntity<>(orderService.createOrder(createOrderModel), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<BookOrder> getOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

}
