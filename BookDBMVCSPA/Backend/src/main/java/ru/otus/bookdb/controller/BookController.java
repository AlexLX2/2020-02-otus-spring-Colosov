package ru.otus.bookdb.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.bookdb.dto.BookDTO;
import ru.otus.bookdb.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> readAllBooks() {
        return bookService.readAllBooks();
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("{id}")
    public BookDTO getBookById(@PathVariable("id") long id) {
        return new BookDTO(bookService.getBookById(id));
    }

    @PostMapping
    public void addBook(@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
    }

    @PutMapping
    public void saveBook(@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
    }
}
