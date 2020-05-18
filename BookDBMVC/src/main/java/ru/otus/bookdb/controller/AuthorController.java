package ru.otus.bookdb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface AuthorController {

    @GetMapping("/author")
    String readAllAuthors(Model model);

    @GetMapping("/author/edit")
    String editAuthor(Long id, Model model);

    @PostMapping("/author/edit")
    String saveAuthor(Long id, String name);
}
