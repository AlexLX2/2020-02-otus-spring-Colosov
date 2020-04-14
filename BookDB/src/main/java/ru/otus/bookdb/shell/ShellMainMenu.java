package ru.otus.bookdb.shell;


import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdb.controller.BookController;
import ru.otus.bookdb.domain.Book;

import java.util.List;

@ShellComponent
public class ShellMainMenu {
    private final BookController controller;

    public ShellMainMenu(BookController controller) {
        this.controller = controller;
    }

    @ShellMethod(value = "List all books", key = {"l", "list"})
    public void listAllBooks() {
        List<Book> bookList = controller.readAllBooks();
        System.out.println(bookList);
    }

    @SneakyThrows
    @ShellMethod(value = "Add a book", key = {"a", "add"})
    public void addBook(
            @ShellOption(value = "-t", help = "Book title") String title,
            @ShellOption(value = "-g", help = "Book genre") String genre,
            @ShellOption(value = "-a", help = "Book author") String author
    ) {
        controller.addBook(title, author, genre);
    }

    @ShellMethod(value = "Update a book", key = {"u", "upd", "update"})
    public void updateBook(
            @ShellOption(value = "-id", help = "Book Id") Long id,
            @ShellOption(value = "-t", help = "Book title") String title,
            @ShellOption(value = "-g", help = "Book genre") String genre,
            @ShellOption(value = "-a", help = "Book author") String author
    ) {
        controller.updateBook(id, title, genre, author);
    }

    @ShellMethod(value = "Delete a book", key = {"d", "del", "delete"})
    public void deleteBook(long id) {
        controller.deleteBookById(id);
    }

}
