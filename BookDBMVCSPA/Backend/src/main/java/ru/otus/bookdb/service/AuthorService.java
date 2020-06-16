package ru.otus.bookdb.service;

import org.springframework.stereotype.Service;
import ru.otus.bookdb.domain.Author;
import ru.otus.bookdb.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> readAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getById(long id) {
        return authorRepository.getById(id);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.delete(authorRepository.getById(id));
    }
}
