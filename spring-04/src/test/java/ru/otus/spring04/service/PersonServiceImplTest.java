package ru.otus.spring04.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring04.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class PersonServiceImplTest {

    private static final String FIRST_NAME = "Alex";
    private static final String LAST_NAME = "Smith";
    private static final int SCORE = 5;

    @Autowired
    PersonService personService;

    @BeforeEach
    void setUp() {
        given(personService.getName()).willReturn(new Person(FIRST_NAME, LAST_NAME));
    }

    private Person person;

    @Test
    void init() {
    }

    @Test
    @DisplayName("создан человек")
    void getName() {
        person = personService.getName();
        assertThat(person).isNotNull();
    }
}