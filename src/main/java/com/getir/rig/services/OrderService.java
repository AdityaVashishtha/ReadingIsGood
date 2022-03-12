package com.getir.rig.services;

import com.getir.rig.entities.Book;
import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Customer;
import com.getir.rig.models.CreateOrderModel;
import com.getir.rig.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import static com.getir.rig.entities.BookOrder.OrderStatus.ORDERED;

@Service
public class OrderService {

    private final BookService bookService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    public OrderService(BookService bookService, CustomerService customerService, OrderRepository orderRepository) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public BookOrder createOrder(CreateOrderModel orderModel) {
        BookOrder order = new BookOrder();
        Book book = bookService.getBookById(orderModel.getBookId());
        Customer customer = customerService.getCustomerByEmail(orderModel.getEmail());
        order.setBook(book);
        order.setCustomer(customer);
        order.setDate(new Date(System.currentTimeMillis()));
        order.setQuantity(orderModel.getQuantity());
        order.setOrderStatus(ORDERED);
        return orderRepository.save(order);
    }

    public List<BookOrder> getOrdersForCustomerId(long customerId) {
        return orderRepository.findBookOrdersByCustomer(customerId);
    }

    public BookOrder getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid Order Id"));
    }
}
