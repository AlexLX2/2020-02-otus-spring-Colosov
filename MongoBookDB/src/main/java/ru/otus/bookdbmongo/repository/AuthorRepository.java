package ru.otus.bookdbmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.bookdbmongo.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String authorName);
}
