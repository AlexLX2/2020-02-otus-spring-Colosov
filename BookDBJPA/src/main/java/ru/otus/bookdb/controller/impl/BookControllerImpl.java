package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.BookController;
import ru.otus.bookdb.dao.AuthorDao;
import ru.otus.bookdb.dao.BookDao;
import ru.otus.bookdb.dao.GenreDao;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

@Controller
public class BookControllerImpl implements BookController {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookControllerImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void addBook(String title, String author, String genre) {
        Genre dbGenre = genreDao.getOrCreateGenreByName(genre);
        Author dbAuthor = authorDao.getOrCreateAuthorByName(author);

        Book book = new Book(0, title, dbGenre, dbAuthor);
        bookDao.addBook(book);
    }

    @Override
    public List<Book> readAllBooks() {
        return bookDao.readAllBooks();
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteBookByID(id);
    }

    @Override
    public void updateBook(long id, String title, String genre, String author) {
        Genre dbGenre = genreDao.getOrCreateGenreByName(genre);
        Author dbAuthor = authorDao.getOrCreateAuthorByName(author);
        Book book = new Book(id, title, dbGenre, dbAuthor);
        bookDao.updateBook(book);
    }

    @Override
    public Optional<Book> getBookByID(long id) {
        return bookDao.getByID(id);
    }

}
