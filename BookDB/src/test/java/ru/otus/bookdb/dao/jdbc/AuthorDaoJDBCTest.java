package ru.otus.bookdb.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ДАО авторов")
@JdbcTest
@Import(AuthorDaoJDBC.class)
class AuthorDaoJDBCTest {

    @Autowired
    private AuthorDaoJDBC authorDao;

    @DisplayName("находит автора по ИД")
    @Test
    void shouldGetAuthorByID() {
        Author expectedAuthor = new Author(1L, "Mark Twain");
        Optional<Author> actualAuthor = authorDao.getByID(1L);
        assertThat(actualAuthor).isPresent().get().isEqualToComparingFieldByField(expectedAuthor);
    }

    @DisplayName("находит автора по имени или создает новый")
    @Test
    void shouldGetOrCreateAuthorByName() {
        String expectedName = "Mark Twain";
        String actualName = authorDao.getOrCreateAuthorByName(expectedName).getName();
        assertEquals(expectedName, actualName);
    }
}