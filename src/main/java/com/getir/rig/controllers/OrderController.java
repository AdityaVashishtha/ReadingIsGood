package com.getir.rig.controllers;

import com.getir.rig.entities.BookOrder;
import com.getir.rig.models.CreateOrderModel;
import com.getir.rig.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.getir.rig.config.ApplicationConstant.ORDER_PATH;

@RestController
@RequestMapping(ORDER_PATH)
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<BookOrder> newOrder(@RequestBody CreateOrderModel createOrderModel) {
        return new ResponseEntity<>(orderService.createOrder(createOrderModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BookOrder> getOrder(@PathParam("orderId") long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

}
