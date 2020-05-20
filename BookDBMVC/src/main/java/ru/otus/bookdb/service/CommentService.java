package ru.otus.bookdb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.repository.BookRepository;
import ru.otus.bookdb.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> readCommentsForABook(long id) {
        return commentRepository.findAllByBook_Id(id);
    }

    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment getById(long id) {
        return commentRepository.getById(id);
    }

    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    public long getBookIdForCommentId(long id) {
        return commentRepository.getById(id)
                .getBook().getId();
    }
}
