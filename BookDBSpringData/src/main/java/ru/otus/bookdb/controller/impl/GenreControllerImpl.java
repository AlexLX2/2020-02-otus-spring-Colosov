package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.GenreController;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.repository.GenreRepository;

import java.util.List;

@Controller
public class GenreControllerImpl implements GenreController {

    private final GenreRepository genreRepository;

    public GenreControllerImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> readAllGenres() {
        return genreRepository.findAll();
    }


    @Override
    public void addGenre(String genreName) {
        genreRepository.save(new Genre(0, genreName));
    }

}
