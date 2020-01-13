package abhijit.das.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

/*
 * @SpringBootApplication annotation. Behind the scenes, that’s equivalent to @Configuration, @EnableAutoConfiguration, and @ComponentScan together
 */

@SpringBootApplication(exclude={JacksonAutoConfiguration.class, JmxAutoConfiguration.class})
public class JustGifItApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustGifItApplication.class, args);
	}
	

}
