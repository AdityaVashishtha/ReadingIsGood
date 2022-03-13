package com.getir.rig.entities;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Customer extends RepresentationModel<Customer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<BookOrder> orders;

}
