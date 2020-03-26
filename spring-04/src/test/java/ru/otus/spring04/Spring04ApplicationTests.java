package ru.otus.spring04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring04.dao.PersonDao;
import ru.otus.spring04.service.PersonService;
import ru.otus.spring04.service.PersonServiceImpl;

@SpringBootTest
class Spring04ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private PersonDao personDao;

    @Value("${scoreToPass}")
    private int passScore;

    private PersonService personService;

    @BeforeEach
    void setUp() {

        personService = new PersonServiceImpl(personDao, passScore);

    }

    @Test
    void checkLocales() {

    }


}
