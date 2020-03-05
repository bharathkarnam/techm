package com.techmahi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TechmahiApplication {
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	public static void main(String[] args) {
		SpringApplication.run(TechmahiApplication.class, args);
	}
	
	@GetMapping(value = "/ssl-test")
    public String greeting(){
        return "Self Signed SSL is Working!!";
    }

}
