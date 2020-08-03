package team.YongAndJoe.NewsTodayBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//TODO config data source
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NewsTodayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsTodayBackendApplication.class, args);
	}

}
