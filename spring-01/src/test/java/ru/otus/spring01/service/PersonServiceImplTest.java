package ru.otus.spring01.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.spring01.dao.PersonDao;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    @Value("${scoreToPass}")
    private int passScore;

    private PersonService personService;

    @BeforeEach
    void setUp() {

        personService = new PersonServiceImpl(personDao, passScore);

    }


}