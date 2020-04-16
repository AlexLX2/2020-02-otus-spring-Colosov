package ru.otus.bookdb.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.GenreDao;
import ru.otus.bookdb.domain.Genre;

import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection", "ConstantConditions"})
public class GenreDaoJDBC implements GenreDao {


    private final NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Optional<Genre> getByID(long id) {
        try {
            return Optional.ofNullable(jdbcOperations.queryForObject("select * from genres where id = :id", Map.of("id", id), new Genre.GenreRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Genre getOrCreateGenreByName(String genreName) {
        Genre genre;
        try {
            genre = jdbcOperations.queryForObject("select * from genres where name = :name", Map.of("name", genreName), new Genre.GenreRowMapper());
        } catch (EmptyResultDataAccessException e) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("name", genreName);

            jdbcOperations.update("insert into genres (name) values (:name)", parameterSource, keyHolder);
            genre = new Genre(keyHolder.getKey().longValue(), genreName);
        }

        return genre;
    }
}
