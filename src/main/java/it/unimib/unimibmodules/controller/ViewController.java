package it.unimib.unimibmodules.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling views.
 * @author Davide Costantini
 * @version 0.3.0
 */
@Controller
public class ViewController {

	@GetMapping(path = "/")
	public ModelAndView index() {

		return new ModelAndView("index.html");
	}
}
