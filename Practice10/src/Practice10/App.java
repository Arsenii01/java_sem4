package Practice10;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class Voldemort implements Magician {
    public void doMagic() {
        System.out.println("Avada Kedavra!");
    }
}

@Component
class HarryPotter implements Magician {
    public void doMagic() {
        System.out.println("Expiliarmus!");
    }
}
@Component
class RonWeesly implements Magician {
    @Override
    public void doMagic() {
        System.out.println("Sektumsempra!");
    }
}

@Configuration
class BeanConfig {
    @Bean
    public Voldemort voldemort() {
        return new Voldemort();
    }

    @Bean
    public HarryPotter harrypotter() {
        return new HarryPotter();
    }

    @Bean
    public RonWeesly ronweesly() {
        return new RonWeesly();
    }
}

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(BeanConfig.class);
        Magician wizard = context.getBean(RonWeesly.class);
        wizard.doMagic();
    }

}
