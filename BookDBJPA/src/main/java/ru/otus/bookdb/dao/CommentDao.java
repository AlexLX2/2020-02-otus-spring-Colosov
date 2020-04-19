package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> readAllComments(Book book);

    void addComment(Comment comment);

    void deleteComment(long id);

    void updateComment(long id, String text);
}
