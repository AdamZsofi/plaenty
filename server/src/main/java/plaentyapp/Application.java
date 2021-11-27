package plaentyapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableJpaAuditing
@EnableWebSecurity
public class Application extends WebSecurityConfigurerAdapter implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplication(Application.class).run(args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO rendbetenni, user, stb.
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/configuration")
				.authenticated().and()
//				.permitAll().and()
			.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/configuration**")
				.authenticated().and()
//				.permitAll().and()
			.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/active-configuration**")
				.authenticated().and()
//				.permitAll().and()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/user")
				.authenticated().and()
//				.rememberMe().and()
//				.permitAll().and()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/login/*", "/error/*", "/configuration/*", "/sensor/*", "/active-configuration/*", "/dashboard*")
				.permitAll().and();
//			.oauth2Login();
	}

	@Override
	public void run(String... args) {}
}
