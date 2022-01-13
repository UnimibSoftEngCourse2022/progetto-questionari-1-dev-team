package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.model.Category;
import it.unimib.unimibmodules.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling HTTP requests related to Category.
 * @author Lorenzo Occhipinti
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api")
public class CategoryController{

    /**
     * Instance of AnswerRepository that will be used to access the db.
     */
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    /**
     * Gets the Category associated with the given id.
     * @param	id	the id of the category
     * @return		an HTTP response with status 200, 500 otherwise
     */
    @GetMapping(path = "/getCategory/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category category = categoryRepository.get(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
