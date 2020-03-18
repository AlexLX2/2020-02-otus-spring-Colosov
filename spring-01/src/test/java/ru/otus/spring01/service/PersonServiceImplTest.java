package ru.otus.spring01.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring01.dao.PersonDao;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonService personService;
    private BufferedReader reader;

    @BeforeEach
    void setUp() throws IOException {

        personService = new PersonServiceImpl(personDao);
        reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("AAA");

    }

    @Test
    @DisplayName("считывает строку")
    void shouldGetRowFromConsole() {
        assertThat("AAA").isEqualTo(personService.getRowFromConsole(reader));
    }

//    @Test
//    @DisplayName("возвращает результат")
//    void getScore() {
//        assertThat(personService.getScore()).isGreaterThanOrEqualTo(0);
//    }
}