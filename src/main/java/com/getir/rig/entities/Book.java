package com.getir.rig.entities;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    @Column(unique = true)
    private String isbn;
    private String description;
    private int year;
    private String publisher;
    private double cost;
    private int quantity;

    @OneToMany(mappedBy = "book")
    private List<BookOrder> orders;

}
