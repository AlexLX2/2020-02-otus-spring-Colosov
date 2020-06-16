package ru.otus.bookdb.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> readAllAuthors() {
        return authorService.readAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") Long id){
        return authorService.getById(id);
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    @PutMapping
    public void editAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
    }

}
