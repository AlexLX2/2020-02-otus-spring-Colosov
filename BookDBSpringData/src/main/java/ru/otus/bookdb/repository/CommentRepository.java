package ru.otus.bookdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteById(long id);

    Comment getById(long id);
}
