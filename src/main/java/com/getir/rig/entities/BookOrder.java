package com.getir.rig.entities;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class BookOrder extends RepresentationModel<BookOrder> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    private OrderStatus orderStatus;
    private int quantity;
    private Date date;

    public static enum OrderStatus {
        ORDERED,
        SHIPPED,
        DELIVERED;
    }

}
