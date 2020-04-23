package ru.otus.bookdb.dao;

import ru.otus.bookdb.domain.Comment;

public interface CommentDao {

    void addComment(Comment comment);

    void deleteComment(long id);

    void updateComment(Comment comment);

    Comment getCommentById(long id);
}
