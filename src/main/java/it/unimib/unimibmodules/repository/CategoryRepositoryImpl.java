package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.CategoryRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for the Category class. Adds business logic to Category instances before
 * accessing the database via DAO.
 * @author Lorenzo Occhipinti
 * @version 0.3.0
 */
@Component("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {

    /**
     * The instance of categoryDAO that will be used to perform actions to the DB
     */
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryRepositoryImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Inserts an instance of Category in the database
     * @param   category  an instance of Category
     */
    public void add(Category category) {
        categoryDAO.save(category);
    }

    /**
     * Inserts a list of categories in the database
     * @param   categoryList  a list of categories
     */
    public void addAll(List<Category> categoryList) {
        categoryDAO.saveAll(categoryList);
    }

    /**
     * Finds the category identified by id in the database
     * @param   id  the id of the category to be found
     * @return      an instance of Category if there is a category identified by id, null otherwise
     * @see CategoryRepository#get(int id)
     */
    @Override
    public Category get(int id) throws NotFoundException {
        Optional<Category> category = categoryDAO.findById(id);
        try {
            return category.orElseThrow();
        }catch (NoSuchElementException ex){
            throw new NotFoundException("The category with id = "+id+" has not been found.");
        }
    }

    /**
     * Returns all categories in the database.
     * @return  a list of Category
     */
    @Override
    public Iterable<Category> getAll() {
        return categoryDAO.findAll();
    }

    /**
     * Deletes from the database the category identified by id.
     * @param   id  the id of the category to be deleted
     */
    public void remove(int id) {
        categoryDAO.deleteById(id);
    }

    /**
     * Deletes all categories in the database.
     */
    public void removeAll() {
        categoryDAO.deleteAll();
    }

    /**
     * Updates a category in the database using a new instance of Category.
     * @param   category  the new instance of Category
     */
    public void modify(Category category) {
        categoryDAO.save(category);
    }
}
