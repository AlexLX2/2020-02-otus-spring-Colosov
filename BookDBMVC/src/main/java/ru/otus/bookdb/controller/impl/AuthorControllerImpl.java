package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.bookdb.controller.AuthorController;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.service.AuthorService;

import java.util.List;

@Controller
public class AuthorControllerImpl implements AuthorController {
    private final AuthorService authorService;

    public AuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping("/author")
    public String readAllAuthors(Model model) {
        List<Author> authors = authorService.readAllAuthors();
        model.addAttribute("authors", authors);
        return "/authors/authors_list";
    }

    @Override
    @GetMapping("/author/edit")
    public String editAuthor(Long id, Model model) {
        model.addAttribute("author", authorService.getById(id));
        return "/authors/authors_edit";
    }

    @Override
    @PostMapping("/author/edit")
    public String saveAuthor(Long id, String name) {
        Author author = new Author(id, name);
        authorService.saveAuthor(author);
        return "redirect:/author";
    }

}
