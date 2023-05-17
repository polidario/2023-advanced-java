import fr.epita.advjava.UsersDAO;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringApplicationContext {


    @Bean("testDI")
    public String stringHello(){
        return "hello from DI!";
    }


    @Bean
    public DataSource mainDatasource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test", "user","user");
    }

    @Bean
    public UsersDAO dao(@Autowired DataSource dataSource){
        return new UsersDAO(dataSource);
    }



}
