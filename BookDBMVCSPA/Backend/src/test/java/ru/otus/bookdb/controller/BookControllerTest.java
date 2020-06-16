package ru.otus.bookdb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;
import ru.otus.bookdb.dto.BookDTO;
import ru.otus.bookdb.service.BookService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер книг должен")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private final List<BookDTO> BOOKDTOLIST = Collections.singletonList(new BookDTO(1, "Tom", "Drama", "Mark"));
    @MockBean
    BookService bookService;

    @Autowired
    BookController controller;
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    @DisplayName("получить и показать все книги")
    public void shouldReadAllBooks() {
        given(bookService.readAllBooks()).willReturn(BOOKDTOLIST);
        MvcResult result = mockMvc.perform(get("/books")).andReturn();
        assertThat(result.getResponse().getContentAsString())
                .contains("Tom")
                .contains("Drama")
                .contains("Mark");
    }

    @SneakyThrows
    @Test
    @DisplayName("пробовать добавлять книгу")
    public void shouldAddBook() {
        String bookBody = new ObjectMapper().writeValueAsString(new BookDTO(0, "test book", "test author", "test genre"));
        mockMvc.perform(post("/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookBody))
                .andExpect(status().isOk());
        verify(bookService).saveBook(new BookDTO(0, "test book", "test author"
                , "test genre"));
    }

    @SneakyThrows
    @Test
    @DisplayName("пробовать редактировать книгу")
    public void shouldEditBook() {
        BookDTO bookDTO = new BookDTO(0, "test book", "test author", "test genre");
        controller.saveBook(bookDTO);
        verify(bookService).saveBook(new BookDTO(0, "test book", "test author"
                , "test genre"));
    }

    @SneakyThrows
    @Test
    @DisplayName("пробовать удалить книгу")
    public void shouldDeleteBook() {
        mockMvc.perform(delete("/books/0"))
                .andExpect(status().isOk());
        verify(bookService).deleteBookById(0);
    }

}