package ru.otus.spring04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring04.domain.Person;
import ru.otus.spring04.service.PersonService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    @Autowired
    PersonService personService;

    private Person person;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void getName() {
        this.person = personService.getName();
    }

    @ShellMethod(value = "Go testing", key = {"test", "t"})
    @ShellMethodAvailability(value = "isPersonCreated")
    public void goTesting() {
        personService.passTest(person);
    }

    private Availability isPersonCreated() {
        return person == null ? Availability.unavailable("Сначала представьтесь") : Availability.available();
    }
}
