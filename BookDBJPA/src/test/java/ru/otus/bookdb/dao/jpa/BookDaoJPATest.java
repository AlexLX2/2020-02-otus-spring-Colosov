package ru.otus.bookdb.dao.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий книг должен")
@DataJpaTest
@Import({BookDaoJPA.class, GenreDaoJPA.class, AuthorDaoJPA.class})
class BookDaoJPATest {

    private final Book EXPECTED_BOOK = new Book(1L, "Tom Soyer", new Genre(1L, "Drama"), new Author(1L, "Mark Twain"));
    private final Book EXPECTED_UPDATED_BOOK = new Book(1L, "Han Solo", new Genre(1L, "Drama"), new Author(1L, "Mark Twain"));
    private final int EXPECTED_BOOK_COUNT = 4;
    @Autowired
    private BookDaoJPA bookDao;
    @Autowired
    private GenreDaoJPA genreDao;
    @Autowired
    private AuthorDaoJPA authorDao;

    @Test
    @DisplayName("находит по ИД")
    void shouldGetByID() {
        Optional<Book> actualBook = bookDao.getByID(1L);
        assertThat(actualBook).isPresent().get().isEqualToComparingOnlyGivenFields(EXPECTED_BOOK, "author", "genre", "title");
    }

    @Test
    @DisplayName("находит все книги")
    void shouldReadAllBooks() {
        assertThat(EXPECTED_BOOK_COUNT).isEqualTo(bookDao.readAllBooks().size());
    }

    @Test
    @DisplayName("считает количество книг")
    void shouldCountAllBooks() {
        assertThat(EXPECTED_BOOK_COUNT).isEqualTo(bookDao.count());
    }

    @Test
    @DisplayName("обновляет книгу")
    void shouldUpdateBook() {
        bookDao.updateBook(EXPECTED_UPDATED_BOOK);
        assertThat(bookDao.getByID(EXPECTED_UPDATED_BOOK.getId())).isPresent().get().usingRecursiveComparison().isEqualTo(EXPECTED_UPDATED_BOOK);
    }

    @Test
    @DisplayName("удаляет книгу по ИД")
    void shouldDeleteBookByID() {
        bookDao.deleteBookByID(EXPECTED_BOOK.getId());
        List<Book> books = bookDao.readAllBooks();
        assertThat(books).doesNotContain(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("добавляет книгу")
    void shouldAddBook() {
        Author expectedAuthor = authorDao.getOrCreateAuthorByName("George Lucas");
        Genre expectedGenre = genreDao.getOrCreateGenreByName("Sci-Fi");
        String title = "Star Wars";
        Book addedBook = new Book(0, title, expectedGenre, expectedAuthor);
        bookDao.addBook(addedBook);
        assertThat(bookDao.readAllBooks()).contains(addedBook);
    }
}