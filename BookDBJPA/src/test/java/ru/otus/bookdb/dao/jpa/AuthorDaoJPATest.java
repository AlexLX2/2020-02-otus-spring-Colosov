package ru.otus.bookdb.dao.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий авторов должен")
@DataJpaTest
@Import(AuthorDaoJPA.class)
class AuthorDaoJPATest {

    private static final int EXPECTED_AUTHORS_COUNT = 4;
    private static final Author EXPECTED_AUTHOR = new Author(1, "Mark Twain");
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorDaoJPA authorDaoJPA;

    @Test
    @DisplayName("должен возвращать всех авторов")
    public void shouldReadAllAuthors() {
        int actualCount = authorDaoJPA.readAllAuthors().size();
        assertThat(actualCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @Test
    @DisplayName("должен возвращать ид автора или создавать нового по имени")
    public void shouldGetOrCreatAuthorByName() {
        Author actualAuthor = authorDaoJPA.getOrCreateAuthorByName(EXPECTED_AUTHOR.getName());
        assertThat(actualAuthor).isEqualToComparingFieldByField(EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("должен находить автора по ИД")
    public void shouldGetAuthorByID() {
        Author expectedAuthor = em.find(Author.class, EXPECTED_AUTHOR.getId());
        Author actualAuthor = authorDaoJPA.getByID(EXPECTED_AUTHOR.getId()).get();
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

}