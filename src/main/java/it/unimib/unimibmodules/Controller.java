package it.unimib.unimibmodules;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	
	 @GetMapping("/")
     public String healthCheck() {
             return "HELLO WORLD!";
     }
}
