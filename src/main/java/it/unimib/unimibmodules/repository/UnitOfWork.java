package it.unimib.unimibmodules.repository;

/**
 * Defines the method that will be used by controllers to implement a Unit of Work
 * @author Davide Costantini
 * @version 1.0.0
 */
public interface UnitOfWork<T> {

	/**
	 * Adds <code>entity</code> to the elements to be inserted.
	 * @param	entity	a new entity of type T
	 */
	void registerNew(T entity);

	/**
	 * Adds <code>entity</code> to the elements to be modified.
	 * @param	entity	a new entity of type T that will replace the entity with the same id
	 */
	void registerModified(T entity);

	/**
	 * Adds <code>entity</code> to the elements to be deleted.
	 * @param	entity	the entity of type T to be deleted
	 */
	void registerDeleted(T entity);

	/**
	 * Commits the changes made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	void commit(int surveyId, int userId);
}
