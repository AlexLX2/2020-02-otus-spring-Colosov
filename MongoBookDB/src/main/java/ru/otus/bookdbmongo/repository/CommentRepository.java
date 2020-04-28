package ru.otus.bookdbmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.bookdbmongo.domain.Book;
import ru.otus.bookdbmongo.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByBook(Book book);

    void deleteAllByBook(Book book);

    int countAllByBook(Book book);

    @SuppressWarnings("NullableProblems")
    Optional<Comment> findById(String id);
}
