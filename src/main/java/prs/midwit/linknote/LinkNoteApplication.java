package prs.midwit.linknote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LinkNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkNoteApplication.class, args);
    }

}
