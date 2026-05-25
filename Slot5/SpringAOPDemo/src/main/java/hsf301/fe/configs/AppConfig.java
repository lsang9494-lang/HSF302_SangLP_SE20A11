package hsf301.fe.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import hsf301.fe.services.StudentService;
import hsf301.fe.services.StudentServiceImpl;
import hsf301.fe.aspects.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public StudentService myService() { return new StudentServiceImpl(); }

    @Bean
    public LoggingAspect loggingAspect() { return new LoggingAspect(); }
}
