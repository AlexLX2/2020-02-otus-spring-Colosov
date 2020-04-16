package ru.otus.bookdb.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ДАО книг")
@JdbcTest
@Import({BookDaoJDBC.class, GenreDaoJDBC.class, AuthorDaoJDBC.class})
class BookDaoJDBCTest {

    private final Book EXPECTED_BOOK = new Book(1L, "Tom Soyer", new Genre(1L, "Drama"), new Author(1L, "Mark Twain"));
    private final Book EXPECTED_UPDATED_BOOK = new Book(1L, "Han Solo", new Genre(1L, "Drama"), new Author(1L, "Mark Twain"));
    private final int EXPECTED_BOOK_COUNT = 4;
    @Autowired
    private BookDaoJDBC bookDao;
    @Autowired
    private GenreDaoJDBC genreDao;
    @Autowired
    private AuthorDaoJDBC authorDao;

    @Test
    @DisplayName("находит по ИД")
    void shouldGetByID() {
        Optional<Book> actualBook = bookDao.getByID(1L);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
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
    void updateBook() {
        bookDao.updateBook(EXPECTED_UPDATED_BOOK.getId(), EXPECTED_UPDATED_BOOK.getTitle(), EXPECTED_UPDATED_BOOK.getAuthor(), EXPECTED_UPDATED_BOOK.getGenre());
        assertThat(bookDao.getByID(EXPECTED_UPDATED_BOOK.getId())).isPresent().get().usingRecursiveComparison().isEqualTo(EXPECTED_UPDATED_BOOK);
    }

    @Test
    @DisplayName("удаляет книгу по ИД")
    void deleteBookByID() {
        bookDao.deleteBookByID(EXPECTED_BOOK.getId());
        List<Book> books = bookDao.readAllBooks();
        assertThat(books).doesNotContain(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("добавляет книгу")
    void addBook() {
        Author expectedAuthor = authorDao.getOrCreateAuthorByName("George Lucas");
        Genre expectedGenre = genreDao.getOrCreateGenreByName("Sci-Fi");
        String title = "Star Wars";
        Long id = bookDao.addBook(title, expectedAuthor, expectedGenre);
        Book expectedBook = new Book(id, title, expectedGenre, expectedAuthor);
        assertThat(bookDao.getByID(id)).isPresent().get().usingRecursiveComparison().ignoringFields("genre, author").isEqualTo(expectedBook);
    }
}