package it.unimib.unimibmodules.repository;


import it.unimib.unimibmodules.dao.AnswerDAO;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.unitofwork.UnitOfWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the Answer and CloseEndedAnswer. Adds business logic to Answer instances before actually accessing
 * the database via DAO.
 * @author Davide Costantini
 * @version 0.0.1
 */
@Component("answerRepository")
public class AnswerRepository implements Repository<Answer>, UnitOfWork<Answer>  {

    /**
     * The instance of AnswerDAO that will be used to perform actions to the DB
     */
    private final AnswerDAO answerDAO;

    @Autowired
    public AnswerRepository(AnswerDAO answerDAO) {

        this.answerDAO = answerDAO;
    }

    /**
     * Inserts an instance of Answer in the database
     * @param   answer  an instance of Answer
     * @see Repository#add
     */
    public void add(Answer answer) {

        answerDAO.save(answer);
    }

    /**
     * Inserts a list of answers in the database
     * @param   answerList  a list of Answers
     * @see Repository#addAll
     */
    public void addAll(List<Answer> answerList) {

        answerDAO.saveAll(answerList);
    }

    /**
     * Finds the answer identified by id in the database
     * @param   id  the id of the answer to be found
     * @return      an instance of Answer if there is an answer identified by id, null otherwise
     * @see Repository#get(int id)
     */
    public Optional<Answer> get(int id) {

        return answerDAO.findById(id);
    }

    /**
     * Returns all answers in the database.
     * @see Repository#getAll()
     * @return  a list of Answers
     */
    public Iterable<Answer> getAll() {

        return answerDAO.findAll();
    }

    /**
     * Deletes from the database the answer identified by id.
     * @param   id  the id of the answer to be deleted
     * @see Repository#remove(int id)
     */
    public void remove(int id) {

        answerDAO.deleteById(id);
    }

    /**
     * Deletes all answers in the database.
     * @see Repository#removeAll()
     */
    public void removeAll() {

        answerDAO.deleteAll();
    }

    /**
     * Updates an answer in the database using a new instance of Answer.
     * @param   answer  the new instance of Answer
     * @see Repository#modify
     */
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

		register(answer, UnitOfWork.INSERT);
	}

	/**
	 * @param   answer
	 * @see UnitOfWork#registerModified
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void registerModified(Answer answer) {

		register(answer, UnitOfWork.MODIFY);
	}

	/**
	 * @param   answer
	 * @see UnitOfWork#registerDeleted
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void registerDeleted(Answer answer) {

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
