package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import ru.otus.bookdb.controller.GenreController;
import ru.otus.bookdb.dao.GenreDao;
import ru.otus.bookdb.domain.Genre;

import java.util.List;

@Controller
public class GenreControllerImpl implements GenreController {

    private final GenreDao genreDao;

    public GenreControllerImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> readAllGenres() {
        return genreDao.readAllGenres();
    }


    @Override
    public void addGenre(String genreName) {
        genreDao.getOrCreateGenreByName(genreName);
    }

}
