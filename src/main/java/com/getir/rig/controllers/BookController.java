package com.getir.rig.controllers;

import com.getir.rig.entities.Book;
import com.getir.rig.models.UpdateBookRequestModel;
import com.getir.rig.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.getir.rig.config.ApplicationConstant.BOOK_PATH;

@RestController
@RequestMapping(BOOK_PATH)
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathParam("bookId") long bookId, @RequestBody UpdateBookRequestModel book) {
        return new ResponseEntity<>(bookService.updateBook(bookId, book.getQuantity()), HttpStatus.OK);
    }

}
