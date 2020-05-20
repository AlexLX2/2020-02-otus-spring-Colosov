package ru.otus.bookdb.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;
import ru.otus.bookdb.controller.impl.CommentControllerImpl;
import ru.otus.bookdb.domain.Comment;
import ru.otus.bookdb.service.BookService;
import ru.otus.bookdb.service.CommentService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(CommentControllerImpl.class)
@DisplayName("Контроллер коментов должен")
class CommentControllerImplTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @MockBean
    BookService bookService;

    @Mock
    View view;

    @Autowired
    CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(commentController)
                .setSingleView(view)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться получить список коментов для книги")
    public void shouldReadCommentsForABook() {
        mockMvc.perform(get("/book/comments")
                .param("id", "0"))
                .andExpect(status().isOk());

        verify(bookService).getBookById(0L);
        verify(commentService).readCommentsForABook(0L);

    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться добавить комент книге")
    public void shouldAddCommentForABook() {
        mockMvc.perform(post("/book/comments/add")
                .param("bookid", "0")
                .param("text", "sample text")
        ).andExpect(status().isOk());

        verify(commentService).saveComment(new Comment(0, "sample text", bookService.getBookById(0L)));
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться получить комент по ид")
    public void shouldGetCommentById() {
        mockMvc.perform(get("/book/comments/edit")
                .param("id", "0"))
                .andExpect(status().isOk());

        verify(commentService).getById(0L);
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться удалить комент по ид")
    public void shouldDeleteCommentById() {
        mockMvc.perform(get("/book/comments/delete")
                .param("id", "0")).andExpect(status().isOk());

        verify(commentService).deleteById(0L);
    }
}