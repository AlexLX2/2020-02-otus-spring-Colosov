package ru.otus.bookdb.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ДАО жанров")
@JdbcTest
@Import(GenreDaoJDBC.class)
class GenreDaoJDBCTest {

    @Autowired
    private GenreDaoJDBC genreDao;

    @DisplayName("находит жанр по ИД")
    @Test
    void shouldGetGenreByID() {
        Genre expectedGenre = new Genre(1L, "Drama");
        Optional<Genre> actualGenre = genreDao.getByID(1L);
        assertThat(actualGenre).isPresent().get().isEqualToComparingFieldByField(expectedGenre);
    }

    @DisplayName("находит жанр по имени или создает новый")
    @Test
    void shouldGetOrCreateGenreByName() {
        String expectedTitle = "Drama";
        Genre actualGenre = genreDao.getOrCreateGenreByName(expectedTitle);
        assertEquals(expectedTitle, actualGenre.getName());
    }
}