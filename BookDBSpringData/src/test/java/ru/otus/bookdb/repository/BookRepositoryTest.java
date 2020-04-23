package ru.otus.bookdb.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий книг должен")
class BookRepositoryTest {

    private final int EXAMPLE_BOOK_ID = 1;
    private final int EXPECTED_COMMENT_COUNT = 4;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("получить книгу с комментариями по ИД")
    public void shouldGetBookByIdWithComments() {
        int countComments = bookRepository.getById(EXAMPLE_BOOK_ID).getComments().size();
        assertThat(countComments).isEqualTo(EXPECTED_COMMENT_COUNT);
    }
}