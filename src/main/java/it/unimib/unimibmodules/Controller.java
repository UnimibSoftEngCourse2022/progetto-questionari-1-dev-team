package it.unimib.unimibmodules;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;


@RestController
public class Controller {
	
	 @GetMapping("/")
     public String healthCheck() {
             return "HELLO WORLD!";
     }
}
