package com.getir.rig.repositories;

import com.getir.rig.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByIsbn(String isbn);
}
