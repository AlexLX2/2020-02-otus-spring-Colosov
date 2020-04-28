package ru.otus.bookdbmongo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.bookdbmongo.domain.Author;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Comment;
import ru.otus.bookdbmongo.domain.Genre;

import java.util.ArrayList;
import java.util.Collections;

@ChangeLog
public class InitAndFillMongoDB {

    private Author authorTwain;
    private Author authorOrwell;

    private Book book1;
    private Book book2;

    @ChangeSet(order = "000", id = "dropDB", author = "AK", runAlways = true)
    public void dropDB(MongoDatabase mongoDatabase) {
        mongoDatabase.drop();
    }

    @ChangeSet(order = "004", id = "fillGenres", author = "AK", runAlways = true)
    public void fiilGenres(MongoTemplate template) {

        template.save(new Genre(null, "Fantasy", Collections.singletonList(book1)));
        template.save(new Genre(null, "Drama", Collections.singletonList(book2)));
        template.save(new Genre(null, "Detective", new ArrayList<>()));
        template.save(new Genre(null, "Sci-fi", new ArrayList<>()));
    }

    @ChangeSet(order = "001", id = "fillAuthors", author = "AK", runAlways = true)
    public void fillAuthors(MongoTemplate template) {
        authorOrwell = new Author(null, "James Orwell");
        authorTwain = new Author(null, "Mark Twain");
        template.save(authorTwain);
        template.save(new Author(null, "Andjey Sapkovski"));
        template.save(new Author(null, "George Martin"));
        template.save(authorOrwell);
    }

    @ChangeSet(order = "003", id = "fillComments", author = "AK", runAlways = true)
    public void fillComments(MongoTemplate template) {
        Comment comment1 = new Comment(null, "Text1", book1);
        Comment comment2 = new Comment(null, "Text2", book1);
        Comment comment3 = new Comment(null, "Text3", book2);
        template.save(comment1);
        template.save(comment2);
        template.save(comment3);
    }

    @ChangeSet(order = "002", id = "fillBooks", author = "AK", runAlways = true)
    public void fillBooks(MongoTemplate template) {
        book1 = new Book(null, "1984", authorOrwell);
        template.save(book1);
        book2 = new Book(null, "Tom Soyer", authorTwain);
        template.save(book2);
    }
}
