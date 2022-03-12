package com.getir.rig.models;

import lombok.Data;

@Data
public class CreateOrderModel {
    private String email;
    private long bookId;
    private int quantity;

}
