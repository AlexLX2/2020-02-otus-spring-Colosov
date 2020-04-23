package ru.otus.bookdb.shell;


import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdb.controller.BookController;
import ru.otus.bookdb.domain.Book;

import java.util.List;

@ShellComponent(value = "Book menu")
public class ShellBookMenu {
    private final BookController bookController;

    public ShellBookMenu(BookController bookController) {
        this.bookController = bookController;
    }

    @ShellMethod(value = "List all books", key = {"l", "list"})
    public void listAllBooks() {
        List<Book> bookList = bookController.readAllBooks();
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
            @ShellOption(value = "-id", help = "Book Id") long id,
            @ShellOption(value = "-t", help = "Book title") String title,
            @ShellOption(value = "-g", help = "Book genre") String genre,
            @ShellOption(value = "-a", help = "Book author") String author
    ) {
        bookController.updateBook(id, title, genre, author);
    }

    @ShellMethod(value = "Delete a book", key = {"d", "del", "delete"})
    public void deleteBook(@ShellOption(value = "-id", help = "Book id") long id) {
        bookController.deleteBookById(id);
    }

    @ShellMethod(value = "Get book by ID", key = {"g", "get"})
    public void getBookByID(@ShellOption(value = "-id", help = "Book id") long id) {
        Book book = bookController.getBookByID(id);
        System.out.println(book);
    }


}
