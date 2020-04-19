package ru.otus.bookdb.dao.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий жанров должен")
@DataJpaTest
@Import(GenreDaoJPA.class)
class GenreDaoJPATest {

    private static final Genre EXPECTED_GENRE = new Genre(1, "Drama");
    private static final int EXPECTED_GENRE_COUNT = 2;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private GenreDaoJPA genreDaoJPA;

    @Test
    @DisplayName("находить жанр по ИД")
    public void shouldGetGenreById() {
        Genre expectedGenre = em.find(Genre.class, EXPECTED_GENRE.getId());
        Genre actualGenre = genreDaoJPA.getByID(EXPECTED_GENRE.getId()).get();
        assertThat(actualGenre).isEqualToComparingFieldByField(expectedGenre);
    }

    @Test
    @DisplayName("получать все жанры")
    public void shouldReadAllGenres() {
        int actualGenreCount = genreDaoJPA.readAllGenres().size();
        assertThat(actualGenreCount).isEqualTo(EXPECTED_GENRE_COUNT);
    }

    @Test
    @DisplayName("должен возвращать ид автора или создавать нового по имени")
    public void shouldGetOrCreatAuthorByName() {
        Genre actualGenre = genreDaoJPA.getOrCreateGenreByName(EXPECTED_GENRE.getName());
        assertThat(actualGenre).isEqualToComparingFieldByField(EXPECTED_GENRE);
    }

}