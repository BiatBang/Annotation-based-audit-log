package com.bang.annotationaudit.repository;

import com.bang.annotationaudit.model.Book;
import com.bang.annotationaudit.model.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookRepository {
    private final Map<Long, Book> bookRepository;
    private final Long DIGIT_LENGTH = 100000000000000L;

    public BookRepository() {
        bookRepository = new ConcurrentHashMap<>();
    }

    public Book createBook(Book book) {
        Long bookId = generateBookId();
        book.setId(bookId);
        return bookRepository.putIfAbsent(bookId, book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.values());
    }

    public Book getBookById(Long id) {
        return bookRepository.getOrDefault(id, null);
    }

    public Book updateBook(Long id, Book book) {
        if (!bookRepository.containsKey(id)) {
            throw new NotFoundException("No book found");
        }
        return bookRepository.put(id, book);
    }

    public void deleteBookById(Long id) {
        bookRepository.remove(id);
    }

    private Long generateBookId() {
        long bookId = (long) (Math.random() * DIGIT_LENGTH);
        while (bookRepository.containsKey(bookId)) {
            bookId = (long) (Math.random() * DIGIT_LENGTH);
        }
        return bookId;
    }
}
