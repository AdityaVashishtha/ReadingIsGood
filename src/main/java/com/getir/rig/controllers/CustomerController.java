package com.getir.rig.controllers;

import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Customer;
import com.getir.rig.entities.Report;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.services.CustomerService;
import com.getir.rig.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.getir.rig.config.ApplicationConstant.CUSTOMER_PATH;

@RestController
@RequestMapping(CUSTOMER_PATH)
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.addCustomer(customerModel), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<BookOrder>> getCustomerOrder(@PathVariable("customerId") Long id) {
        return new ResponseEntity<>(orderService.getOrdersForCustomerId(id), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/stats")
    public ResponseEntity<List<Report>> getCustomerStats(@PathVariable("customerId") Long id) {
        return new ResponseEntity<>(orderService.getStatsReport(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Invalid Request");
        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
