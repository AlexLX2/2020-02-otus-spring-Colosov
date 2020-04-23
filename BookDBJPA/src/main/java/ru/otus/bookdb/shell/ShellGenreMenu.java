package ru.otus.bookdb.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bookdb.controller.GenreController;
import ru.otus.bookdb.domain.Genre;

import java.util.List;

@ShellComponent(value = "Genres menu")
public class ShellGenreMenu {
    private final GenreController genreController;

    public ShellGenreMenu(GenreController genreController) {
        this.genreController = genreController;
    }

    @ShellMethod(value = "List all genres", key = {"lg", "list-genres"})
    public void listAllGenres() {
        List<Genre> genreList = genreController.readAllGenres();
        System.out.println(genreList);
    }

    @ShellMethod(value = "Add genre", key = {"ag", "add-genre"})
    public void addGenre(@ShellOption(value = "-n", help = "Genre name") String genreName) {
        genreController.addGenre(genreName);
    }

}
