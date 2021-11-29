package hu.bme.aut.plaentyapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableJpaAuditing
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplication(Application.class).run(args);
	}

	@Override
	public void run(String... args) {}
}
