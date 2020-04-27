package ru.otus.bookdb.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.bookdb.controller.AuthorController;
import ru.otus.bookdb.domain.Author;

import java.util.List;

@ShellComponent(value = "Authors menu")
public class ShellAuthorMenu {
    private final AuthorController authorController;

    public ShellAuthorMenu(AuthorController authorController) {
        this.authorController = authorController;
    }

    @ShellMethod(value = "List all authors", key = {"la", "list-authors"})
    public void listAllAuthors() {
        List<Author> authorList = authorController.readAllAuthors();
        System.out.println(authorList);
    }

}
