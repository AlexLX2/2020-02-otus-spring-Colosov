package ru.otus.bookdb.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.dao.CommentDao;
import ru.otus.bookdb.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentDaoJPA implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        em.createQuery("delete from Comment c  where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        em.merge(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }
}