package ru.otus.bookdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getById(long id);

    List<Author> findAll();

    Optional<Author> findByName(String authorName);
}
