package com.getir.rig.repositories;

import com.getir.rig.entities.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findBookOrdersByCustomer(long id);
}
