package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.AuthorDao;
import ru.otus.bookdb.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorDaoJPA implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> getByID(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> readAllAuthors() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getOrCreateAuthorByName(String authorName) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", authorName);
        if (query.getResultList().size() == 0) {
            Author author = new Author(0, authorName);
            em.persist(author);
            return author;
        } else {
            return query.getSingleResult();
        }
    }
}
