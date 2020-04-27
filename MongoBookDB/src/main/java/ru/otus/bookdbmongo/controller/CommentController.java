package ru.otus.bookdbmongo.controller;

import ru.otus.bookdbmongo.domain.Comment;

import java.util.List;

public interface CommentController {
    List<Comment> findCommentForABook(String bookId);

    void addComment(String bookId, String text);

    void updateComment(String id, String bookId, String text);

    void deleteComment(String id);
}
