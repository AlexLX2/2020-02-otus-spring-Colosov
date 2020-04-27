package ru.otus.bookdbmongo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Comment;
import ru.otus.bookdbmongo.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий книг должен ")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.bookdbmongo.changelogs", "ru.otus.bookdbmongo.repository.events"})
public class BooksCascadeEvetnListenerTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("удалять коменты при удалении книги")
    void shouldDeleteCommentsOnDeleteBook() {
        Comment comment = commentRepository.findAll().get(0);
        Book book = comment.getBook();
        long expectedCount = commentRepository.count() - commentRepository.countAllByBook(book);
        bookRepository.deleteBookById(book.getId());
        assertThat(commentRepository.count()).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName(" обновлять список книг у жанра")
    void shouldUpdateGenreOnDeleteBook() {
        Book book = bookRepository.findAll().get(0);
        Genre genre = genreRepository.findAllByBookListContaining(book);
        int expectedCount = genre.getBookList().size() - 1;
        String genreId = genre.getId();
        bookRepository.deleteBookById(book.getId());
        assertThat(genreRepository.findById(genreId).get().getBookList().size()).isEqualTo(expectedCount);
    }
}
