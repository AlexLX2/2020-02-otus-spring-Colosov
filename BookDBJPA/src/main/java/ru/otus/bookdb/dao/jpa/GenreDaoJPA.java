package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.dao.GenreDao;
import ru.otus.bookdb.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoJPA implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> getByID(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> readAllGenres() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    @Transactional
    public Genre getOrCreateGenreByName(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", genreName);
        if (query.getResultList().size() == 0) {
            return new Genre(0, genreName);
        } else {
            return query.getSingleResult();
        }
    }

}
