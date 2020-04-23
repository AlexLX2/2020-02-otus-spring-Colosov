package ru.otus.bookdb.controller;

import ru.otus.bookdb.domain.Comment;

import java.util.List;

public interface CommentController {
    List<Comment> readCommentsForABook(long bookId);

    void addCommentForABook(long bookId, String text);

    void updateComment(long id, String text);

    void deleteComment(long id);
}
