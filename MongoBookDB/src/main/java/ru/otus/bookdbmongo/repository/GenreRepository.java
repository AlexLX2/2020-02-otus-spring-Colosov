package ru.otus.bookdbmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findAllByBookListContaining(Book book);

    Genre findAllByBookListContains(List<Book> bookList);

    Optional<Genre> findByName(String genreName);
}
