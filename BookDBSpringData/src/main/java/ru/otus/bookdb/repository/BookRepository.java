package ru.otus.bookdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join fetch b.author join fetch b.genre join fetch b.comments where b.id = :id")
    Book getById(@Param("id") long id);

    List<Book> findAll();

    long count();

    void deleteById(long id);

}
