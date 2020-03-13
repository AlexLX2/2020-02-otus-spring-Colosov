import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring01.service.PersonService;


public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonService personService = context.getBean(PersonService.class);
        System.out.println("First name: ");
        String firstName = personService.getFirstName();
        System.out.println("Last name: ");
        String lastName = personService.getLastName();
        int score = personService.getScore();
        personService.save(firstName,lastName,score);
        System.out.println("Score:");
        System.out.println(score);



    }
}
