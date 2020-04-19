package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.GenreDao;
import ru.otus.bookdb.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
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
    public Genre getOrCreateGenreByName(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", genreName);
        if (query.getResultList().size() == 0) {
            Genre genre = new Genre(0, genreName);
            em.persist(genre);
            return genre;
        } else {
            return query.getSingleResult();
        }
    }

}
