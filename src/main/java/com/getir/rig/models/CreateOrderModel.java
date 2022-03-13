package com.getir.rig.models;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class CreateOrderModel {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(1)
    private Long bookId;

    @Min(0)
    @Max(1000)
    private int quantity;

}
