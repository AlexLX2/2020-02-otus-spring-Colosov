package ru.otus.bookdb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.repository.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис авторов должен")
@DataJpaTest
@Import(AuthorService.class)
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository repository;

    @Test
    @DisplayName("получить всех авторов")
    void shouldReadAllAuthors() {
        List<Author> authors = authorService.readAllAuthors();
        assertThat(authors).isNotEmpty();
        assertThat(authors.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("получить автора по ИД")
    void shouldGetById() {
        Author actualAuthor = authorService.getById(1);
        Author expectedAuthor = new Author(1, "Mark Twain");
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @Test
    @DisplayName("записать нового автора")
    void shouldSaveAuthor() {
        Author author = new Author(0, "Lev Tolstoy");
        authorService.saveAuthor(author);
        assertThat(authorService.readAllAuthors().size()).isEqualTo(5);
    }
}