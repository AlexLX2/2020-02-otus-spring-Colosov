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
import ru.otus.bookdb.controller.impl.AuthorControllerImpl;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.service.AuthorService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@DisplayName("Контроллер авторов должен")
@WebMvcTest(AuthorControllerImpl.class)
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
        mockMvc.perform(get("/author")).andExpect(status().isOk());
        verify(authorService).readAllAuthors();
    }

    @SneakyThrows
    @Test
    @DisplayName("получить автора по ИД")
    public void shouldGetAuthorById() {
        mockMvc.perform(get("/author/edit")
                .param("id", "0"))
                .andExpect(status().isOk());
        verify(authorService).getById(0L);
    }

    @SneakyThrows
    @Test
    @DisplayName("попробовать записать автора")
    public void shouldSaveAuthor() {
        mockMvc.perform(post("/author/edit")
                .param("id", "0")
                .param("name", "Mark"))
                .andExpect(status().isOk());

        verify(authorService).saveAuthor(new Author(0, "Mark"));
    }
}