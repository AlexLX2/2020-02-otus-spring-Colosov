package ru.otus.bookdb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface GenreController {
    @GetMapping("/genre")
    String readAllGenres(Model model);

    @GetMapping("/genre/edit")
    String editGenre(Long id, Model model);

    @PostMapping("/genre/edit")
    String saveGenre(Long id, String name);

    @PostMapping("/genre/add")
    String addGenre(String genreName);

    @GetMapping("/genre/add")
    String addGenre(Model model);
}
