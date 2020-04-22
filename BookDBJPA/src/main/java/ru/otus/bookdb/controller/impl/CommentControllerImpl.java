package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.CommentController;
import ru.otus.bookdb.dao.BookDao;
import ru.otus.bookdb.dao.CommentDao;
import ru.otus.bookdb.domain.Comment;

import java.util.List;

@Controller
public class CommentControllerImpl implements CommentController {

    private final BookDao bookDao;
    private final CommentDao commentDao;

    public CommentControllerImpl(BookDao bookDao, CommentDao commentDao) {
        this.bookDao = bookDao;
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> readCommentsForABook(long bookId) {
        return bookDao.getByID(bookId).getComments();
    }

    @Override
    public void addCommentForABook(long bookId, String text) {
        Comment comment = new Comment(0, text, bookDao.getByID(bookId));
        commentDao.addComment(comment);
    }

    @Override
    public void updateComment(long id, String text) {
        Comment comment = commentDao.getCommentById(id);
        comment.setText(text);
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentDao.deleteComment(id);
    }
}
