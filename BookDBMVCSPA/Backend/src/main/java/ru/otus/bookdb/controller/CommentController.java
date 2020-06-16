package ru.otus.bookdb.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.service.BookService;
import ru.otus.bookdb.service.CommentService;

import java.util.List;

@RestController
public class CommentController {

    private final BookService bookService;
    private final CommentService commentService;

    public CommentController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping("/books/{bookId}/comments")
    public List<Comment> readCommentsForABook(@PathVariable("bookId") long bookId) {
        return commentService.readCommentsForABook(bookId);
    }

    @GetMapping("/books/{bookId}/comments/{commentId}")
    public Comment getComment(@PathVariable("commentId") long commentId){
        return commentService.getById(commentId);
    }

    @PutMapping("/books/{bookId}/comments/{commentId}")
    public void updateComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
    }

    @DeleteMapping("/books/{bookId}/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") long commentId){
        commentService.deleteById(commentId);
    }

    @PostMapping("/books/{bookId}/comments/add")
    public void addCommentForABook(@PathVariable("bookId") long bookid, @RequestBody Comment comment) {
        comment.setBook(bookService.getBookById(bookid));
        commentService.saveComment(comment);
    }
}
