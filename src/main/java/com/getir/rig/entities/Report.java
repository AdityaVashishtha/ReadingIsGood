package com.getir.rig.entities;

import lombok.Data;

public interface Report {
    String getMonth();
    Long getOrders();
    Long getBooks();
    Double getTotalAmount();
}
