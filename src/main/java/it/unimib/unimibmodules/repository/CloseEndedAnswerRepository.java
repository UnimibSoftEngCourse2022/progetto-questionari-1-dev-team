package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.dao.CloseEndedAnswerDAO;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for CloseEndedAnswers. Adds business logic to CloseEndedAnswers instances before actually accessing
 * the database via DAO.
 * @author Davide Costantini
 * @version 0.1.0
 */
@Component("closeEndedAnswerRepository")
public class CloseEndedAnswerRepository implements Repository<CloseEndedAnswer> {

	/**
	 * The instance of CloseEndedAnswerDAO that will be used to perform actions to the DB
	 */
	private final CloseEndedAnswerDAO closeEndedAnswerDAO;

	@Autowired
	public CloseEndedAnswerRepository(CloseEndedAnswerDAO closeEndedAnswerDAO) {

		this.closeEndedAnswerDAO = closeEndedAnswerDAO;
	}

	/**
	 * Inserts an instance of CloseEndedAnswer in the database
	 * @param   closeEndedAnswer	an instance of CloseEndedAnswer
	 * @see Repository#add
	 */
	public void add(CloseEndedAnswer closeEndedAnswer) {

		closeEndedAnswerDAO.save(closeEndedAnswer);
	}

	/**
	 * Inserts a list of CloseEndedAnswer in the database
	 * @param   closeEndedAnswerList	a list of CloseEndedAnswer
	 * @see Repository#addall
	 */
	public void addall(List<CloseEndedAnswer> closeEndedAnswerList) {

		closeEndedAnswerDAO.saveAll(closeEndedAnswerList);
	}

	/**
	 * Finds the CloseEndedAnswer identified by <code>id</code> in the database.
	 * @param   id					the id of the CloseEndedAnswer to be found
	 * @return						an instance of CloseEndedAnswer if there is a close-ended answer identified by
	 * 								<code>id</code>
	 * @throws	NotFoundException	if no close-ended answer identified by <code>id</code> has been found
	 * @see Repository#get(int id)
	 */
	public CloseEndedAnswer get(int id) throws NotFoundException {

		Optional<CloseEndedAnswer> closeEndedAnswer = closeEndedAnswerDAO.findById(id);
		try {
			return closeEndedAnswer.orElseThrow();
		} catch (NoSuchElementException e) {
			throw new NotFoundException("No ClosedEndedAnswer with id " + id + " was found.");
		}
	}

	/**
	 * Returns all CloseEndedAnswer in the database.
	 * @see Repository#getAll()
	 * @return	a list of CloseEndedAnswer
	 */
	public Iterable<CloseEndedAnswer> getAll() {

		return closeEndedAnswerDAO.findAll();
	}

	/**
	 * Deletes from the database the CloseEndedAnswer identified by <code>id</code>.
	 * @param	id					the id of the CloseEndedAnswer to be deleted
	 * @throws	NotFoundException	if no close-ended answer identified by <code>id</code> has been found
	 * @see Repository#remove(int id)
	 */
	public void remove(int id) throws NotFoundException {

		try {
			closeEndedAnswerDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("No ClosedEndedAnswer with id " + id + " was found.");
		}
	}

	/**
	 * Deletes all CloseEndedAnswer in the database.
	 * @see Repository#removeAll()
	 */
	public void removeAll() {

		closeEndedAnswerDAO.deleteAll();
	}

	/**
	 * Updates a CloseEndedAnswer in the database using a new instance of CloseEndedAnswer.
	 * @param	closeEndedAnswer	the new instance of CloseEndedAnswer
	 * @see Repository#modify
	 */
	public void modify(CloseEndedAnswer closeEndedAnswer) {

		closeEndedAnswerDAO.save(closeEndedAnswer);
	}
}
