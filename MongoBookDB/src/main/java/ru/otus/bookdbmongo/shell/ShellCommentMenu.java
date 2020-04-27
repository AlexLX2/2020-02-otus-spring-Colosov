package ru.otus.bookdbmongo.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdbmongo.controller.CommentController;
import ru.otus.bookdbmongo.domain.Comment;

import java.util.List;

@ShellComponent(value = "Comments menu")
public class ShellCommentMenu {
    private final CommentController commentController;

    public ShellCommentMenu(CommentController commentController) {
        this.commentController = commentController;
    }

    @ShellMethod(value = "List comments for a book", key = {"lc", "list-comments"})
    public void listComments(
            @ShellOption(value = "-id", help = "Book id") String bookId) {
        List<Comment> commentList = commentController.findCommentForABook(bookId);
        System.out.println(commentList);
    }

    @ShellMethod(value = "Add comment for a book", key = {"ac", "add-comment"})
    public void addCommentForABook(
            @ShellOption(value = "-id", help = "Book id") String bookId,
            @ShellOption(value = "-text", help = "Comment") String text
    ) {
        commentController.addComment(bookId, text);
    }

    @ShellMethod(value = "Update comment for a book", key = {"uc", "update-comment"})
    public void updateCommentForABook(
            @ShellOption(value = "-id", help = "Comment id") String id,
            @ShellOption(value = "-bid", help = "Book id") String bookId,
            @ShellOption(value = "-text", help = "Comment") String text
    ) {
        commentController.updateComment(id, bookId, text);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "delete-comment"})
    public void deleteComment(
            @ShellOption(value = "-id", help = "Comment id") String id
    ) {
        commentController.deleteComment(id);
    }
}
