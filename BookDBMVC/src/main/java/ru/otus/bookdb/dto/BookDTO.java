package ru.otus.bookdb.dto;

import lombok.Data;
import ru.otus.bookdb.domain.Book;

@Data
public class BookDTO {
    String title;
    String authorName;
    String genreName;
    long id;

    public BookDTO(Book book) {
        this.authorName = book.getAuthor().getName();
        this.genreName = book.getGenre().getName();
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public BookDTO(long id, String title, String authorName, String genreName) {
        this.title = title;
        this.authorName = authorName;
        this.genreName = genreName;
        this.id = id;
    }
}

