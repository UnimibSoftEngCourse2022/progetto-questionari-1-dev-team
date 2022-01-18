package it.unimib.unimibmodules.repository;


import it.unimib.unimibmodules.controller.AnswerRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for the Answers. Adds business logic to Answer instances before actually accessing
 * the database via DAO.
 * @author Davide Costantini
 * @version 0.1.0
 */
@Component("answerRepository")
public class AnswerRepositoryImpl implements AnswerRepository, UnitOfWork<Answer>  {

    /**
     * The instance of AnswerDAO that will be used to perform actions to the DB
     */
    private final AnswerDAO answerDAO;

    @Autowired
    public AnswerRepositoryImpl(AnswerDAO answerDAO) {

        this.answerDAO = answerDAO;
    }

    /**
     * Inserts an instance of Answer in the database
     * @param   answer  an instance of Answer
     * @see AnswerRepository#add
     */
	@Override
    public void add(Answer answer) {

        answerDAO.save(answer);
    }

    /**
     * Inserts a list of answers in the database
     * @param   answerList  a list of Answers
     */
    public void addAll(List<Answer> answerList) {

        answerDAO.saveAll(answerList);
    }

    /**
     * Finds the answer identified by id in the database
     * @param	id					the id of the answer to be found
     * @return						an instance of Answer if there is an answer identified by id, null otherwise
	 * @throws	NotFoundException	if no answer identified by <code>id</code> has been found
     * @see AnswerRepository#get(int id)
     */
	@Override
    public Answer get(int id) throws NotFoundException {

		Optional<Answer> answer = answerDAO.findById(id);
		try {
			return answer.orElseThrow();
		} catch (NoSuchElementException e) {
			throw new NotFoundException("No Answer with id " + id + " was found.");
		}
    }

    /**
     * Returns all answers in the database.
     * @return  a list of Answers
     */
    public Iterable<Answer> getAll() {

        return answerDAO.findAll();
    }

    /**
     * Deletes from the database the answer identified by id.
     * @param   id					the id of the answer to be deleted
	 * @throws	NotFoundException	if no answer identified by <code>id</code> has been found
     * @see AnswerRepository#remove(int id)
     */
	@Override
    public void remove(int id) throws NotFoundException {

		try {
			answerDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("No ClosedEndedAnswer with id " + id + " was found.");
		}
    }

    /**
     * Deletes all answers in the database.
     */
    public void removeAll() {

        answerDAO.deleteAll();
    }

    /**
     * Updates an answer in the database using a new instance of Answer.
     * @param   answer  the new instance of Answer
     * @see AnswerRepository#modify
     */
	@Override
    public void modify(Answer answer) {

        answerDAO.save(answer);
    }
    
    /**
	 * @param   answer
	 * @param   operation
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void register(Answer answer, String operation) {

		// TODO Auto-generated method stub
	}

	/**
	 * @param   answer
	 * @see UnitOfWork#registerNew
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void registerNew(Answer answer) {

		// TODO Auto-generated method stub
		register(answer, UnitOfWork.INSERT);
	}

	/**
	 * @param   answer
	 * @see UnitOfWork#registerModified
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void registerModified(Answer answer) {

		// TODO Auto-generated method stub
		register(answer, UnitOfWork.MODIFY);
	}

	/**
	 * @param   answer
	 * @see UnitOfWork#registerDeleted
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void registerDeleted(Answer answer) {

		// TODO Auto-generated method stub
		register(answer, UnitOfWork.DELETE);
	}

	/**
	 * @see UnitOfWork#commit
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void commit() {

		// TODO Auto-generated method stub
	}
}
