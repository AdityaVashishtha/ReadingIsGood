package com.getir.rig.services;

import com.getir.rig.entities.Book;
import com.getir.rig.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    public Book getBookById(long id) {
        return bookRepository.getById(id);
    }


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(long bookId, int quantity) {
        Book currentBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));
        currentBook.setQuantity(quantity);
        bookRepository.save(currentBook);
        return currentBook;
    }
}
