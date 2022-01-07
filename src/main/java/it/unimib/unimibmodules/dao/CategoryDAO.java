package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Category class.
 * @author Lorenzo Occhipinti
 */
public interface CategoryDAO extends CrudRepository<Category, Integer> {

}
