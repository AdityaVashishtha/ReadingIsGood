package com.getir.rig.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@ToString
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Column(unique = true, length = 32)
    @NotBlank
    @Size(max = 32)
    private String isbn;

    private String description;

    @Min(1500)
    @Max(2050)
    private int year;

    @Size(max = 255)
    private String publisher;

    @DecimalMin("0.01")
    @DecimalMax("1000.0")
    private double cost;

    @Min(0)
    @Max(1000)
    private int quantity;


}
