package ru.otus.bookdbmongo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.bookdbmongo.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий жанров должен ")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.bookdbmongo.changelogs", "ru.otus.bookdbmongo.repository.events"})
public class GenreCascadeEventListener {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenreRepository genreRepository;

    @Test
    @DisplayName("удалять книги при удалении жанра")
    void shouldDeleteBooksOnDeleteGenre() {
        Genre genre = genreRepository.findAll().get(0);
        long expectedCount = bookRepository.count() - genre.getBookList().size();
        genreRepository.delete(genre);
        assertThat(bookRepository.count()).isEqualTo(expectedCount);
    }
}
