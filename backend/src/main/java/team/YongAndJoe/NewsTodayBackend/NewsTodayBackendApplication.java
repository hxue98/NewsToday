package team.YongAndJoe.NewsTodayBackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("team.YongAndJoe.NewsTodayBackend.dao")
public class NewsTodayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsTodayBackendApplication.class, args);
	}

}
