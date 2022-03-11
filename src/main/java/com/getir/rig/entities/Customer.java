package com.getir.rig.entities;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Validated
public class Customer extends RepresentationModel<Customer> {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String name;


}
