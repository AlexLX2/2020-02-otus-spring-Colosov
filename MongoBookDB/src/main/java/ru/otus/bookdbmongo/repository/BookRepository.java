package ru.otus.bookdbmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.bookdbmongo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findBookByTitleIgnoreCase(String title);

    void deleteBookById(String id);

    List<Book> findAllByAuthor_Name(String authorName);

    @SuppressWarnings({"override", "NullableProblems"})
    Optional<Book> findById(String id);
}
