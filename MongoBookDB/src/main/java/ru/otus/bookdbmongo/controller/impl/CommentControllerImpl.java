package ru.otus.bookdbmongo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.bookdbmongo.controller.CommentController;
import ru.otus.bookdbmongo.domain.Comment;
import ru.otus.bookdbmongo.repository.BookRepository;
import ru.otus.bookdbmongo.repository.CommentRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentControllerImpl implements CommentController {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Comment> findCommentForABook(String bookId) {
        return commentRepository.findAllByBook(bookRepository.findById(bookId).get());
    }

    @Override
    public void addComment(String bookId, String text) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setBook(bookRepository.findById(bookId).get());
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(String id, String bookId, String text) {
        Comment comment = commentRepository.findById(id).get();
        comment.setBook(bookRepository.findById(bookId).get());
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}
