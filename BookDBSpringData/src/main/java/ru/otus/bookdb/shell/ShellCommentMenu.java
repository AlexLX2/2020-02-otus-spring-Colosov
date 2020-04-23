package ru.otus.bookdb.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdb.controller.CommentController;
import ru.otus.bookdb.domain.Comment;

import java.util.List;

@ShellComponent(value = "Comments menu")
public class ShellCommentMenu {
    private final CommentController commentController;

    public ShellCommentMenu(CommentController commentController) {
        this.commentController = commentController;
    }

    @ShellMethod(value = "List comments on a book", key = {"lc", "list-comments"})
    public void listComments(
            @ShellOption(value = "-id", help = "Book id") long id) {
        List<Comment> commentList = commentController.readCommentsForABook(id);
        System.out.println(commentList);
    }

    @ShellMethod(value = "Add comment for a book", key = {"ac", "add-comment"})
    public void addCommentForABook(
            @ShellOption(value = "-id", help = "Book id") long id,
            @ShellOption(value = "-text", help = "Comment") String text
    ) {
        commentController.addCommentForABook(id, text);
    }

    @ShellMethod(value = "Update comment for a book", key = {"uc", "update-comment"})
    public void updateCommentForABook(
            @ShellOption(value = "-id", help = "Comment id") long id,
            @ShellOption(value = "-text", help = "Comment") String text
    ) {
        commentController.updateComment(id, text);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "delete-comment"})
    public void deleteComment(
            @ShellOption(value = "-id", help = "Comment id") long id
    ) {
        commentController.deleteComment(id);
    }
}
