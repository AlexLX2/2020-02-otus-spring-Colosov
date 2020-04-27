package ru.otus.bookdbmongo.repository.events;


import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Genre;
import ru.otus.bookdbmongo.repository.BookRepository;
import ru.otus.bookdbmongo.repository.CommentRepository;
import ru.otus.bookdbmongo.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class BooksCascadeEvetnListener extends AbstractMongoEventListener<Book> {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);

        Book book = bookRepository.findById(event.getSource().get("_id").toString()).orElse(null);
        Genre genre = genreRepository.findAllByBookListContaining(book);
        genre.getBookList().remove(book);
        genreRepository.save(genre);
        commentRepository.deleteAllByBook(book);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Book> event) {
        super.onAfterSave(event);

    }
}
