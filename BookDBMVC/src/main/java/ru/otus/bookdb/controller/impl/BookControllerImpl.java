package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.bookdb.controller.BookController;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.dto.BookDTO;
import ru.otus.bookdb.service.BookService;

import java.util.List;

@Controller
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping("/book")
    public String readAllBooks(Model model) {
        List<BookDTO> books = bookService.readAllBooks();
        model.addAttribute("books", books);
        return "/books/books_list";
    }

    @Override
    @GetMapping("/book/delete")
    public String deleteBookById(long id) {
        bookService.deleteBookById(id);
        return "redirect:/book";
    }

    @Override
    @GetMapping("/book/add")
    public String addBook(Model model) {
        model.addAttribute(new Book());
        return "/books/books_edit";
    }

    @Override
    @PostMapping("/book/add")
    public String addBook(String title, String authorName, String genreName) {
        BookDTO bookDTO = new BookDTO(0, title, authorName, genreName);
        bookService.saveBook(bookDTO);
        return "redirect:/book";
    }

    @Override
    @GetMapping("/book/edit")
    public String editBook(long id, Model model) {
        model.addAttribute(bookService.getBookById(id));
        return "/books/books_edit";
    }

    @Override
    @PostMapping("/book/edit")
    public String saveBook(long id, String title, String genreName, String authorName, Model model) {
        BookDTO bookDTO = new BookDTO(id, title, authorName, genreName);
        Book book = bookService.saveBook(bookDTO);
        model.addAttribute(book);
        return "redirect:/book";
    }

}
