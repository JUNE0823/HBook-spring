package hb.hbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HbookApplication {


	// swagger ui endpoint
	// http://localhost:7777/swagger-ui/index.html
	public static void main(String[] args) {
		SpringApplication.run(HbookApplication.class, args);
	}

}
