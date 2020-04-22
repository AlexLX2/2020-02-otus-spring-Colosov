package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.dao.BookDao;
import ru.otus.bookdb.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDaoJPA implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book getByID(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b" +
                " join fetch b.author" +
                " join fetch b.genre " +
                " join fetch b.comments where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> readAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b" +
                " join fetch b.author" +
                " join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public int count() {
        return em.createQuery("select count(b) from Book b", Long.class).getSingleResult().intValue();
    }

    @Override
    public void deleteBookByID(long id) {
        em.remove(em.find(Book.class, id));
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        if (book.getGenre().getId() == 0) {
            em.persist(book.getGenre());
        }
        if (book.getAuthor().getId() == 0) {
            em.persist(book.getAuthor());
        }
        em.persist(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        if (book.getGenre().getId() == 0) {
            em.persist(book.getGenre());
        }
        if (book.getAuthor().getId() == 0) {
            em.persist(book.getAuthor());
        }
        em.merge(book);
    }

}
