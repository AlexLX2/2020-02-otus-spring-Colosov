package ru.otus.bookdb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.service.BookService;
import ru.otus.bookdb.service.CommentService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер коментов должен")
class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @MockBean
    BookService bookService;

    @Autowired
    CommentController commentController;


    @SneakyThrows
    @Test
    @DisplayName("пытаться получить список коментов для книги")
    public void shouldReadCommentsForABook() {
        mockMvc.perform(get("/books/0/comments"))
                .andExpect(status().isOk());
        verify(commentService).readCommentsForABook(0L);

    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться добавить комент книге")
    public void shouldAddCommentForABook() {
        String commentBody = new ObjectMapper().writeValueAsString(new Comment(0, "sample text", bookService.getBookById(0)));
        mockMvc.perform(post("/books/0/comments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentBody)
        ).andExpect(status().isOk());

        verify(commentService).saveComment(new Comment(0, "sample text", bookService.getBookById(0L)));
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться получить комент по ид")
    public void shouldGetCommentById() {
        mockMvc.perform(get("/books/0/comments/0"))
                .andExpect(status().isOk());

        verify(commentService).getById(0L);
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться удалить комент по ид")
    public void shouldDeleteCommentById() {
        mockMvc.perform(delete("/books/0/comments/0"))
                .andExpect(status().isOk());
        verify(commentService).deleteById(0L);
    }
}