package ru.otus.bookdbmongo.repository.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.bookdbmongo.domain.Genre;
import ru.otus.bookdbmongo.repository.BookRepository;
import ru.otus.bookdbmongo.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class GenreCascadeEventListener extends AbstractMongoEventListener<Genre> {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        genreRepository.findById(event.getSource().get("_id").toString()).ifPresent(genre -> bookRepository.deleteAll(genre.getBookList()));
    }
}
