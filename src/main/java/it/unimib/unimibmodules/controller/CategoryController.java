package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.CategoryDTO;
import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Category;
import it.unimib.unimibmodules.model.Question;
import org.modelmapper.ModelMapper;
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
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api")
public class CategoryController extends DTOMapping<Category, CategoryDTO>{

    /**
     * Instance of AnswerRepository that will be used to access the db.
     */
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryController(CategoryRepository categoryRepository, ModelMapper modelMapper) {

        super(modelMapper);
        this.categoryRepository = categoryRepository;

        modelMapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMappings(mapper -> {
                    mapper.map(Category::getId, CategoryDTO::setId);
                    mapper.map(Category::getName, CategoryDTO::setName);
                });
    }

    /**
     * Gets the Category associated with the given id.
     * @param	id	the id of the category
     * @return		an HTTP response with status 200, 500 otherwise
     * @throws NotFoundException 
     */
    @GetMapping(path = "/getCategory/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int id) throws NotFoundException {
        Category category = categoryRepository.get(id);
        return new ResponseEntity<>(convertToDTO(category), HttpStatus.OK);
    }

    @Override
    public CategoryDTO convertToDTO(Category value) {
        return modelMapper.map(value, CategoryDTO.class);
    }

    @Override
    public Category convertToEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }
}
