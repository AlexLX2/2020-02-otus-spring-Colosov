package ru.otus.bookdbmongo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.bookdbmongo.controller.GenreController;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Genre;
import ru.otus.bookdbmongo.repository.GenreRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreControllerImpl implements GenreController {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void addGenre(String genreName, List<Book> books) {
        Genre genre = new Genre(null, genreName, books);
        genreRepository.save(genre);
    }
}
