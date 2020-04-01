package ru.otus.spring04;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring04.dao.PersonDao;
import ru.otus.spring04.service.PersonService;


@SpringBootTest
class Spring04ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private PersonDao personDao;

    @Value("${scoreToPass}")
    private int passScore;

    @InjectMocks
    private PersonService personService;

    @Test
    void checkLocales() {

    }


}
