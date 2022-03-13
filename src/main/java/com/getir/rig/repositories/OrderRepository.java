package com.getir.rig.repositories;

import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findBookOrdersByCustomerId(Long id);

    @Query(value = "SELECT MONTHNAME(A.DATE) as Month," +
            "count(A.id) as Orders," +
            "sum(A.quantity) as Books," +
            "sum(A.quantity*B.cost) as totalAmount  " +
            "FROM BOOK_ORDER as A, BOOK as B " +
            "WHERE A.CUSTOMER_ID=?1 AND B.ID = A.BOOK_ID AND YEAR(A.DATE) = YEAR(CURRENT_TIMESTAMP) " +
            "GROUP BY MONTHNAME(A.DATE)", nativeQuery=true)
    List<Report> getReport(Long customerId);
}
