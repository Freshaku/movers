package mg.mowers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "mg.mowers")
@EntityScan("mg.mowers.entity")
@ComponentScan(basePackages = {"mg.mowers"})
public class MowersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MowersApplication.class, args);
	}

}
