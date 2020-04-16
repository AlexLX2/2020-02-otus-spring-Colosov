package ru.otus.bookdb.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.BookDao;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection", "ConstantConditions"})
@Repository
public class BookDaoJDBC implements BookDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<Book> getByID(long id) {
        try {
            return Optional.ofNullable(jdbcOperations.queryForObject("select b.id, b.title, a.id as author_id, a.name as author_name," +
                    " g.name as genre_name, g.id as genre_id" +
                    " from books b " +
                    "left join authors a on b.author_id = a.id " +
                    "left join genres g on b.genre_id = g.id " +
                    "where b.id = :id", Map.of("id", id), new Book.BookRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> readAllBooks() {
        return jdbcOperations.query("select b.id, b.title, a.id as author_id, a.name as author_name," +
                " g.name as genre_name, g.id as genre_id" +
                " from books b " +
                "left join authors a on b.author_id = a.id " +
                "left join genres g on b.genre_id = g.id ", new Book.BookRowMapper());
    }

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations()
                .queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void updateBook(long id, String title, Author author, Genre genre) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", title);
        parameterSource.addValue("author_id", author.getId());
        parameterSource.addValue("genre_id", genre.getId());
        parameterSource.addValue("id", id);
        jdbcOperations.update("update books set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id", parameterSource);
    }

    @Override
    public void deleteBookByID(long id) {
        jdbcOperations.update("delete from books where id = :id", Map.of("id", id));
    }

    public Long addBook(String title, Author author, Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", title);
        parameterSource.addValue("author_id", author.getId());
        parameterSource.addValue("genre_id", genre.getId());
        jdbcOperations.update("insert into books (title, author_id, genre_id ) values (:title, :author_id, :genre_id )", parameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

}
