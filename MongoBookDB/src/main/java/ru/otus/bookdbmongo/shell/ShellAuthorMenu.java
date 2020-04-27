package ru.otus.bookdbmongo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.bookdbmongo.controller.AuthorController;
import ru.otus.bookdbmongo.domain.Author;

import java.util.List;

@ShellComponent(value = "Authors menu")
public class ShellAuthorMenu {
    private final AuthorController authorController;

    public ShellAuthorMenu(AuthorController authorController) {
        this.authorController = authorController;
    }

    @ShellMethod(value = "List all authors", key = {"la", "list-authors"})
    public void listAllAuthors() {
        List<Author> authorList = authorController.findAll();
        System.out.println(authorList);
    }

}
