package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.dao.CategoryDAO;
import it.unimib.unimibmodules.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the Category class. Adds business logic to Category instances before
 * accessing the database via DAO.
 * @author Lorenzo Occhipinti
 * @version 0.0.1
 */

public class CategoryRepository implements Repository<Category> {

    /**
     * The instance of categoryDAO that will be used to perform actions to the DB
     */
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryRepository(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Inserts an instance of Category in the database
     * @param   category  an instance of Category
     * @see Repository#add
     */
    @Override
    public void add(Category category) {
        categoryDAO.save(category);
    }

    /**
     * Inserts a list of categories in the database
     * @param   categoryList  a list of categories
     * @see Repository#addall
     */
    @Override
    public void addall(List<Category> categoryList) {
        categoryDAO.saveAll(categoryList);
    }

    /**
     * Finds the category identified by id in the database
     * @param   id  the id of the category to be found
     * @return      an instance of Category if there is a category identified by id, null otherwise
     * @see Repository#get(int id)
     */
    @Override
    public Optional<Category> get(int id) {
        return categoryDAO.findById(id);
    }

    /**
     * Returns all categories in the database.
     * @see Repository#getAll()
     * @return  a list of Category
     */
    @Override
    public Iterable<Category> getAll() {
        return categoryDAO.findAll();
    }

    /**
     * Deletes from the database the category identified by id.
     * @param   id  the id of the category to be deleted
     * @see Repository#remove(int id)
     */
    @Override
    public void remove(int id) {
        categoryDAO.deleteById(id);
    }

    /**
     * Deletes all categories in the database.
     * @see Repository#removeAll()
     */
    @Override
    public void removeAll() {
        categoryDAO.deleteAll();
    }

    /**
     * Updates a category in the database using a new instance of Category.
     * @param   category  the new instance of Category
     * @see Repository#modify
     */
    @Override
    public void modify(Category category) {
        categoryDAO.save(category);
    }
}
