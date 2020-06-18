package ru.otus.bookdb.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.bookdb.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b " +
            "join fetch b.author " +
            "join fetch b.genre " +
            "left join fetch b.comments " +
            "where b.id = :id")
    Book getById(@Param("id") long id);

    @EntityGraph("book-entity-graph")
    List<Book> findAll();

    void deleteById(long id);

}
