package com.getir.rig.repositories;

import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    Page<BookOrder> findBookOrdersByCustomerId(Long id, Pageable pageable);

    @Query(value = "SELECT MONTHNAME(A.DATE) as Month," +
            "count(A.id) as Orders," +
            "sum(A.quantity) as Books," +
            "sum(A.quantity*B.cost) as totalAmount  " +
            "FROM BOOK_ORDER as A, BOOK as B " +
            "WHERE A.CUSTOMER_ID=?1 AND B.ID = A.BOOK_ID AND YEAR(A.DATE) = YEAR(CURRENT_TIMESTAMP) " +
            "GROUP BY MONTHNAME(A.DATE)", nativeQuery=true)
    List<Report> getReport(Long customerId);

    @Query(value = "SELECT * FROM BOOK_ORDER WHERE CUSTOMER_ID=?1 AND DATE >= ?2 AND DATE <= ?3", nativeQuery=true)
    Page<BookOrder> findBookOrdersBetweenMonths(Long id, Date after, Date before, Pageable pageable);
}
