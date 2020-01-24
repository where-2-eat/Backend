package ca.mcgill.ecse428.where2eat.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Where2eatBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Where2eatBackendApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting(){
		return "Hello from Where2Eat!";
	}

}
