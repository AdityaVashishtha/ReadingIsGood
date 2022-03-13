package com.getir.rig.controllers;

import com.getir.rig.entities.Book;
import com.getir.rig.models.UpdateBookRequestModel;
import com.getir.rig.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.getir.rig.config.ApplicationConstant.BOOK_PATH;

@RestController
@RequestMapping(BOOK_PATH)
@Slf4j
public class BookController extends ErrorController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        log.info("Adding new book {}", book);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody UpdateBookRequestModel book) {
        log.info("Updating book stock for book id {} to {}", bookId, book);
        return new ResponseEntity<>(bookService.updateBook(bookId, book.getQuantity()), HttpStatus.OK);
    }

}
