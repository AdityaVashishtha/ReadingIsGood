package com.getir.rig.models;

import lombok.Data;

@Data
public class CreateOrderModel {
    private String email;
    private Long bookId;
    private int quantity;

}
