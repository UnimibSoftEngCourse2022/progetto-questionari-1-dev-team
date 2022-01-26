package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.CategoryDTO;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller handling HTTP requests related to Category.
 * @author Lorenzo Occhipinti
 * @author Khalil Mohamed Khalil
 * @version 0.3.0
 */
@RestController
@RequestMapping("/api")
public class CategoryController extends DTOListMapping<Category, CategoryDTO>{

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
    @GetMapping(path = "/findCategory/{id}")
    public ResponseEntity<CategoryDTO> findCategory(@PathVariable int id) throws NotFoundException {
        Category category = categoryRepository.get(id);
        return new ResponseEntity<>(convertToDTO(category), HttpStatus.OK);
    }

    /**
     * Gets all the categories
     * @return		an HTTP response with status 200, 500 otherwise
     * @throws NotFoundException
     */
    @GetMapping(path = "/findCategories")
    public ResponseEntity<List<CategoryDTO>> findCategories () throws NotFoundException{
        Iterable<Category> categoryList = categoryRepository.getAll();
        List<CategoryDTO> categoryListDTO = convertListToDTO(categoryList);
        return new ResponseEntity<>(categoryListDTO, HttpStatus.OK);
    }

    @Override
    public CategoryDTO convertToDTO(Category value) {
        return modelMapper.map(value, CategoryDTO.class);
    }

    @Override
    public Category convertToEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }

    @Override
    public List<CategoryDTO> convertListToDTO(Iterable<Category> categories) {

        List<CategoryDTO> categoryList = new ArrayList<>();
        for (Category c : categories)
            categoryList.add(convertToDTO(c));

        return categoryList;
    }
}
