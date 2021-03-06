package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Category;

/**
 * Interface for CategoryRepository.
 * @author Lorenzo Occhipinti
 * @version 1.0.0
 */
public interface CategoryRepository {

	/**
	 * Finds the category identified by id in the database
	 * @param   id  the id of the category to be found
	 * @return      an instance of Category if there is a category identified by id, null otherwise
	 */
	Category get(int id) throws NotFoundException;

	/**
	 * Finds all the categories in the database
	 * @return      all the instances of Category, null otherwise
	 */
	Iterable<Category> getAll() throws NotFoundException;
}
