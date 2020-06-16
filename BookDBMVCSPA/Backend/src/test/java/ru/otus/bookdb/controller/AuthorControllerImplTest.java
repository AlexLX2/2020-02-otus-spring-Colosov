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
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.service.AuthorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@DisplayName("Контроллер авторов должен")
@WebMvcTest(AuthorController.class)
class AuthorControllerImplTest {
    @Mock
    View mockView;
    @Autowired
    AuthorController authorController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(authorController)
                .setSingleView(mockView)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("получить и показать всех авторов")
    public void shouldListAuthors() {
        mockMvc.perform(get("/authors")).andExpect(status().isOk());
        verify(authorService).readAllAuthors();
    }

    @SneakyThrows
    @Test
    @DisplayName("получить автора по ИД")
    public void shouldGetAuthorById() {
        mockMvc.perform(get("/authors/0"))
                .andExpect(status().isOk());
        verify(authorService).getById(0L);
    }

    @SneakyThrows
    @Test
    @DisplayName("попробовать записать автора")
    public void shouldSaveAuthor() {
        String authorBody = new ObjectMapper().writeValueAsString(new Author(0, "Mark"));
        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorBody))
                .andExpect(status().isOk());
        verify(authorService).saveAuthor(any());
    }
}