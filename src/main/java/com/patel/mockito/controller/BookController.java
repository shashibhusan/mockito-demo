package com.patel.mockito.controller;

import com.patel.mockito.model.Book;
import com.patel.mockito.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBookRecords() {

        return bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @PostMapping
    public Book createBookRecord(@RequestBody Book bookRecord) {
        return bookRepository.save(bookRecord);
    }

    public Book updateBookRecord(@RequestBody Book bookRecord) throws NotFoundException {
        if (null == bookRecord || bookRecord.getBookId() == null) {
            throw new NotFoundException("Not able to find the Book");
        }
        Optional<Book> bookOptional = bookRepository.findById(bookRecord.getBookId());
        if (!bookOptional.isPresent()) {
            throw new NotFoundException("Book with Id: " + bookRecord.getBookId() + " does not exist");
        }
        Book existBookRecord = bookOptional.get();
        existBookRecord.setName(bookRecord.getName());
        existBookRecord.setSummary(bookRecord.getSummary());
        existBookRecord.setRating(bookRecord.getRating());

        return bookRepository.save(existBookRecord);
    }

    // TODO: write delete method using TDD approach

}
