package ru.otus.bookdb.dao.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.bookdb.domain.Book;
import ru.otus.bookdb.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий комментариев должен")
@Import(CommentDaoJPA.class)
class CommentDaoJPATest {
    private static final int EXPECTED_COMMENT_COUNT = 4;
    private static final long BOOK_ID = 1;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private CommentDaoJPA commentDaoJPA;


    @Test
    @DisplayName("должен добавлять комментарий")
    void shouldAddComment() {
        Comment comment = new Comment(0, "Test", em.find(Book.class, BOOK_ID));
        commentDaoJPA.addComment(comment);
        assertThat((em.find(Book.class, BOOK_ID)).getComments().size())
                .isEqualTo(EXPECTED_COMMENT_COUNT + 1);
    }

    @Test
    @DisplayName("должен удалять комментарий по ИД")
    void shouldDeleteComment() {
        commentDaoJPA.deleteComment(1L);
        assertThat((em.find(Book.class, BOOK_ID)).getComments().size())
                .isEqualTo(EXPECTED_COMMENT_COUNT - 1);
    }

    @Test
    @DisplayName("должен обновлять комментарий")
    void shouldUpdateComment() {
        Comment comment = commentDaoJPA.getCommentById(1L);
        comment.setText("Test");
        commentDaoJPA.updateComment(comment);
        assertThat(em.find(Comment.class, 1L).getText()).isEqualTo("Test");
    }
}