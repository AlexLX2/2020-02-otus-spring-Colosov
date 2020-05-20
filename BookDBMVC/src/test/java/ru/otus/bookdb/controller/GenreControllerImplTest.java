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
import ru.otus.bookdb.controller.impl.GenreControllerImpl;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.service.GenreService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@DisplayName("Контроллер жанров должен")
@WebMvcTest(GenreControllerImpl.class)
class GenreControllerImplTest {
    @Mock
    View mockView;
    @Autowired
    GenreController genreController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(genreController)
                .setSingleView(mockView)
                .build();
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться получить все жанры")
    public void shouldReadAllGenres() {
        mockMvc.perform(get("/genre")).andExpect(status().isOk());
        verify(genreService).readAllGenres();
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться добавить жанр")
    public void shouldSaveGenre() {
        mockMvc.perform(post("/genre/add")
                .param("genreName", "Test genre"))
                .andExpect(status().isOk());

        verify(genreService).saveGenre(new Genre(0, "Test genre"));
    }

}