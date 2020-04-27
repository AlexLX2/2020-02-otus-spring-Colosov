package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.controller.CommentController;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.repository.BookRepository;
import ru.otus.bookdb.repository.CommentRepository;

import java.util.List;

@Controller
public class CommentControllerImpl implements CommentController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public CommentControllerImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> readCommentsForABook(long bookId) {
        return bookRepository.getById(bookId).getComments();
    }

    @Override
    @Transactional
    public void addCommentForABook(long bookId, String text) {
        Comment comment = new Comment(0, text, bookRepository.getById(bookId));
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(long id, String text) {
        Comment comment = commentRepository.getById(id);
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
