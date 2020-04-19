package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.bookdb.dao.CommentDao;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDaoJPA implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> readAllComments(Book book) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where  c.book = :book", Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void deleteComment(long id) {
        em.createQuery("delete from Comment c  where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateComment(long id, String text) {
        em.createQuery("update Comment c set c.text=:text where c.id = :id")
                .setParameter("text", text)
                .setParameter("id", id)
                .executeUpdate();
    }
}