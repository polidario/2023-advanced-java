import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationContext {


    @Bean("testDI")
    public String stringHello(){
        return "hello from DI!";
    }
}
