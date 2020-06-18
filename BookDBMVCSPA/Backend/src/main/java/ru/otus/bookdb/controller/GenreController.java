package ru.otus.bookdb.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> readAllGenres() {
        return genreService.readAllGenres();
    }

    @GetMapping("{id}")
    public Genre getGenreById(@PathVariable("id") long id){
        return genreService.getGenreByID(id);
    }

    @PutMapping
    public void updateGenre(@RequestBody Genre genre) {
        genreService.saveGenre(genre);
    }

    @PostMapping
    public void createGenre(@RequestBody Genre genre) {
        genreService.saveGenre(genre);
    }

    @DeleteMapping("{id}")
    public void deleteGenreById(@PathVariable("id") long id){
        genreService.deleteGenreById(id);
    }
}
