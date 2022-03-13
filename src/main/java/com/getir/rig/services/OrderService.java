package com.getir.rig.services;

import com.getir.rig.entities.Book;
import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Customer;
import com.getir.rig.entities.Report;
import com.getir.rig.models.CreateOrderModel;
import com.getir.rig.repositories.BookRepository;
import com.getir.rig.repositories.OrderRepository;
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
    private final BookRepository bookRepository;

    public OrderService(BookService bookService, CustomerService customerService, OrderRepository orderRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
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
        orderRepository.saveAndFlush(order);
        book.setQuantity(book.getQuantity() - order.getQuantity());
        bookRepository.saveAndFlush(book);
        return order;
    }

    public List<BookOrder> getOrdersForCustomerId(Long customerId) {
        return orderRepository.findBookOrdersByCustomerId(customerId);
    }

    public List<Report> getStatsReport(Long customerId) {
        return orderRepository.getReport(customerId);
    }

    public BookOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid Order Id"));
    }
}
