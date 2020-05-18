package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.bookdb.controller.CommentController;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.service.BookService;
import ru.otus.bookdb.service.CommentService;

import java.util.List;

@Controller
public class CommentControllerImpl implements CommentController {

    private final BookService bookService;
    private final CommentService commentService;

    public CommentControllerImpl(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @Override
    @GetMapping("/book/comments")
    public String readCommentsForABook(long id, Model model) {
        Book book = bookService.getBookById(id);
        List<Comment> comments = commentService.readCommentsForABook(id);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        return "/comments/comments_list";
    }

    @Override
    @Transactional
    @PostMapping("/book/comments/add")
    public String addCommentForABook(long bookid, String text, Model model) {
        Comment comment = new Comment(0, text, bookService.getBookById(bookid));
        commentService.saveComment(comment);
        model.addAttribute(comment);
        return "redirect:/book/comments?id=" + bookid;
    }

    @Override
    @GetMapping("book/comments/edit")
    public String editComment(long id, Model model) {
        model.addAttribute("comment", commentService.getById(id));
        return "/comments/comments_edit";
    }

    @Override
    @PostMapping("book/comments/edit")
    public String saveComment(long id, String text, Model model) {
        Comment comment = commentService.getById(id);
        comment.setText(text);
        commentService.saveComment(comment);
        model.addAttribute(comment);
        return "redirect:/book/comments?id=" + comment.getBook().getId();
    }

    @Override
    @GetMapping("book/comments/delete")
    public String deleteComment(long id) {
        long bookId = commentService.getBookIdForCommentId(id);
        commentService.deleteById(id);
        return "redirect:/book/comments?id=" + bookId;
    }
}
