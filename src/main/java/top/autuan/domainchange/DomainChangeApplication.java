package top.autuan.domainchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DomainChangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainChangeApplication.class, args);
    }

}
