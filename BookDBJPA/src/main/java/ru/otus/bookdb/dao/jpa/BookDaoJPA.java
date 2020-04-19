package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.BookDao;
import ru.otus.bookdb.domain.Book;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookDaoJPA implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Book> getByID(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> readAllBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-genre-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "join fetch b.author " +
                "left join fetch b.comments", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public int count() {
        return em.createQuery("select count(b) from Book b", Long.class).getSingleResult().intValue();
    }

    @Override
    public void deleteBookByID(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void addBook(Book book) {
        em.persist(book);
    }

    @Override
    public void updateBook(Book book) {
        em.merge(book);
    }
}
