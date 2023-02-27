package com.bang.annotationaudit.controller;

import com.bang.annotationaudit.aop.AuditableEndpoint;
import com.bang.annotationaudit.model.Book;
import com.bang.annotationaudit.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bang.annotationaudit.model.AuditEvent.CREATE_BOOK;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @AuditableEndpoint(auditEvent = CREATE_BOOK)
    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookRepository.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.getBookById(bookId));
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        bookRepository.updateBook(bookId, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long bookId) {
        bookRepository.deleteBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
