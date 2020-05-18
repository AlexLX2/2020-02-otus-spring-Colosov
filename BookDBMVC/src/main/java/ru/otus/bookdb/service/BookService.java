package ru.otus.bookdb.service;

import org.springframework.stereotype.Service;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.dto.BookDTO;
import ru.otus.bookdb.repository.AuthorRepository;
import ru.otus.bookdb.repository.BookRepository;
import ru.otus.bookdb.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public List<BookDTO> readAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(long id) {
        return bookRepository.getById(id);
    }

    public Book saveBook(BookDTO bookDTO) {
        Genre genre = genreRepository.findByName(bookDTO.getGenreName())
                .orElse(new Genre(0, bookDTO.getGenreName()));
        Author author = authorRepository.findByName(bookDTO.getAuthorName())
                .orElse(new Author(0, bookDTO.getAuthorName()));
        Book book = new Book(bookDTO.getId(), bookDTO.getTitle(), genre, author);
        if (genre.getId() == 0) {
            genreRepository.save(genre);
        }
        if (author.getId() == 0) {
            authorRepository.save(author);
        }
        bookRepository.save(book);
        return book;
    }
}
