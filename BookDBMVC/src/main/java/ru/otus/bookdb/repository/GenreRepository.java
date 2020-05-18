package ru.otus.bookdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getById(long id);

    List<Genre> findAll();

    Optional<Genre> findByName(String genreName);
}
