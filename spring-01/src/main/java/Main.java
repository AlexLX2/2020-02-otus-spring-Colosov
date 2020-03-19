import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring01.service.PersonService;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = "ru.otus.spring01")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        PersonService personService = context.getBean(PersonService.class);
        personService.init();
    }
}
