package ru.otus.bookdb.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CommentController {

    @GetMapping("/book/comments")
    String readCommentsForABook(long id, Model model);

    @Transactional
    @PostMapping("/book/comments/add")
    String addCommentForABook(long bookid, String text, Model model);

    @GetMapping("book/comments/edit")
    String editComment(long id, Model model);

    @PostMapping("book/comments/edit")
    String saveComment(long id, String text, Model model);

    @GetMapping("book/comments/delete")
    String deleteComment(long id);
}
