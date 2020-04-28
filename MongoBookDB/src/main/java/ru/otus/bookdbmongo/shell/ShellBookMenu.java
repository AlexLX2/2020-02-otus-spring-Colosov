package ru.otus.bookdbmongo.shell;


import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdbmongo.controller.BookController;
import ru.otus.bookdbmongo.domain.Book;

import java.util.List;

@ShellComponent(value = "Book menu")
public class ShellBookMenu {
    private final BookController bookController;

    public ShellBookMenu(BookController bookController) {
        this.bookController = bookController;
    }

    @ShellMethod(value = "List all books", key = {"l", "list"})
    public void listAllBooks() {
        List<Book> bookList = bookController.findAll();
        System.out.println(bookList);
    }

    @SneakyThrows
    @ShellMethod(value = "Add a book", key = {"a", "add"})
    public void addBook(
            @ShellOption(value = "-t", help = "Book title") String title,
            @ShellOption(value = "-g", help = "Book genre") String genre,
            @ShellOption(value = "-a", help = "Book author") String author
    ) {
        bookController.addBook(title, author, genre);
    }

    @ShellMethod(value = "Update a book", key = {"u", "upd", "update"})
    public void updateBook(
            @ShellOption(value = "-id", help = "Book Id") String id,
            @ShellOption(value = "-t", help = "Book title") String title,
            @ShellOption(value = "-g", help = "Book genre") String genre,
            @ShellOption(value = "-a", help = "Book author") String author
    ) {
        bookController.updateBook(id, title, author, genre);
    }

    @ShellMethod(value = "Delete a book", key = {"d", "del", "delete"})
    public void deleteBook(@ShellOption(value = "-id", help = "Book id") String id) {
        bookController.deleteBookById(id);
    }

    @ShellMethod(value = "Get book by ID", key = {"g", "get"})
    public void getBookByID(@ShellOption(value = "-id", help = "Book id") String id) {
        Book book = bookController.getById(id);
        System.out.println(book);
    }


}
