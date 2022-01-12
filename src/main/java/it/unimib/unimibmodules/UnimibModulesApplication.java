package it.unimib.unimibmodules;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnimibModulesApplication {

	public static void main(String[] args) {

		SpringApplication.run(UnimibModulesApplication.class, args);
		
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
}
