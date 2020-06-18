package ru.otus.bookdb.service;

import org.springframework.stereotype.Service;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> readAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreByID(long id) {
        return genreRepository.getById(id);
    }

    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteGenreById(long id){ genreRepository.deleteById(id);}

}
