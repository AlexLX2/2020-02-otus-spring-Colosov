package ru.otus.bookdb.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.bookdb.controller.GenreController;
import ru.otus.bookdb.domain.Genre;
import ru.otus.bookdb.service.GenreService;

import java.util.List;

@Controller
public class GenreControllerImpl implements GenreController {

    private final GenreService genreService;

    public GenreControllerImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    @GetMapping("/genre")
    public String readAllGenres(Model model) {
        List<Genre> genres = genreService.readAllGenres();
        model.addAttribute("genres", genres);
        return "/genres/genres_list";
    }

    @Override
    @GetMapping("/genre/edit")
    public String editGenre(Long id, Model model) {
        model.addAttribute("genre", genreService.getGenreByID(id));
        return "/genres/genres_edit";
    }

    @Override
    @PostMapping("/genre/edit")
    public String saveGenre(Long id, String name) {
        Genre genre = new Genre(id, name);
        genreService.saveGenre(genre);
        return "redirect:/genre";
    }


    @Override
    @PostMapping("/genre/add")
    public String addGenre(String genreName) {
        genreService.saveGenre(new Genre(0, genreName));
        return "redirect:/genre";
    }

    @Override
    @GetMapping("/genre/add")
    public String addGenre(Model model) {
        model.addAttribute(new Genre());
        return "/genres/genres_edit";
    }
}
