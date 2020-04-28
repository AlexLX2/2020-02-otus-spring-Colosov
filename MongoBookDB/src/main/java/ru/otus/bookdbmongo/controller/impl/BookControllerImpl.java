package ru.otus.bookdbmongo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.otus.bookdbmongo.controller.BookController;
import ru.otus.bookdbmongo.domain.Author;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Genre;
import ru.otus.bookdbmongo.repository.AuthorRepository;
import ru.otus.bookdbmongo.repository.BookRepository;
import ru.otus.bookdbmongo.repository.GenreRepository;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void addBook(String title, String authorName, String genreName) {
        Author author = getOrCreateAuthor(authorName);
        Book book = new Book(null, title, author);
        bookRepository.save(book);
        updateOrCreateGenre(genreName, book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public void updateBook(String id, String title, String authorName, String genreName) {
        Book book = bookRepository.findById(id).get();
        book.setTitle(title);
        Author author = getOrCreateAuthor(authorName);
        book.setAuthor(author);
        bookRepository.save(book);
        updateOrCreateGenre(genreName, book);
    }

    private void updateOrCreateGenre(String genreName, Book book) {
        Genre genre = genreRepository.findByName(genreName).orElse(new Genre(null, genreName, null));
        if (genre.getId() == null) {
            genre.setBookList(Collections.singletonList(book));
        } else {
            genre.getBookList().add(book);
        }
        genreRepository.save(genre);
    }

    private Author getOrCreateAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName).orElse(new Author(null, authorName));
        if (author.getId() == null) {
            authorRepository.save(author);
        }
        return author;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public Book getById(String id) {
        return bookRepository.findById(id).get();
    }
}
