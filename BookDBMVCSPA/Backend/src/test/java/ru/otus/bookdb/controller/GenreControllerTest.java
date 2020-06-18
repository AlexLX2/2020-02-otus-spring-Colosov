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
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.service.GenreService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@DisplayName("Контроллер жанров должен")
@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    GenreController genreController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GenreService genreService;

    @SneakyThrows
    @Test
    @DisplayName("пытаться получить все жанры")
    public void shouldReadAllGenres() {
        mockMvc.perform(get("/genres")).andExpect(status().isOk());
        verify(genreService).readAllGenres();
    }

    @SneakyThrows
    @Test
    @DisplayName("пытаться добавить жанр")
    public void shouldSaveGenre() {
        String genreBody = new ObjectMapper().writeValueAsString(new Genre(0,"Test genre"));
        mockMvc.perform(post("/genres/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(genreBody))
                .andExpect(status().isOk());
        verify(genreService).saveGenre(new Genre(0, "Test genre"));
    }
}