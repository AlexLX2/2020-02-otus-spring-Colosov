package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.controller.BookController;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.repository.AuthorRepository;
import ru.otus.bookdb.repository.BookRepository;
import ru.otus.bookdb.repository.GenreRepository;

import java.util.List;

@Controller
public class BookControllerImpl implements BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookControllerImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public void addBook(String title, String authorName, String genreName) {
        Genre genre = genreRepository.findByName(genreName).orElse(new Genre(0, genreName));
        Author author = authorRepository.findByName(authorName).orElse(new Author(0, authorName));
        Book book = new Book(0, title, genre, author);
        if (genre.getId() == 0) {
            genreRepository.save(genre);
        }
        if (author.getId() == 0) {
            authorRepository.save(author);
        }
        bookRepository.save(book);
    }

    @Override
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(long id, String title, String genreName, String authorName) {
        Genre genre = genreRepository.findByName(genreName).orElse(new Genre(0, genreName));
        Author author = authorRepository.findByName(authorName).orElse(new Author(0, authorName));
        Book book = new Book(id, title, genre, author);
        if (genre.getId() == 0) {
            genreRepository.save(genre);
        }
        if (author.getId() == 0) {
            authorRepository.save(author);
        }
        bookRepository.save(book);
    }

    @Override
    public Book getBookByID(long id) {
        return bookRepository.getById(id);
    }

}
