package templates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

@Slf4j
@org.springframework.boot.autoconfigure.SpringBootApplication
public class TwoApesApplication {
    public static void main(String[] args) {
        log.info("spring.devtools.restart.enabled");
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(TwoApesApplication.class, args);
    }
}
