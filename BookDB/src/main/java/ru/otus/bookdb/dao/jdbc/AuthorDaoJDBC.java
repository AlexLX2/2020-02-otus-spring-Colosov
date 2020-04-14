package ru.otus.bookdb.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.AuthorDao;
import ru.otus.bookdb.domain.Author;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection", "ConstantConditions"})
@Repository
public class AuthorDaoJDBC implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<Author> getByID(long id) {
        try {
            return Optional.ofNullable(jdbcOperations.queryForObject("select * from authors where id = :id", Map.of("id", id), new Author.AuthorRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Author getOrCreateAuthorByName(String authorName) {
        Author author;
        try {
            author = jdbcOperations.queryForObject("select * from authors where name = :name", Map.of("name", authorName), new Author.AuthorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("name", authorName);

            jdbcOperations.update("insert into authors (name) values (:name)", parameterSource, keyHolder);
            author = new Author(keyHolder.getKey().longValue(), authorName);
        }

        return author;
    }
}
