package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.AnswerRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository for the Answers. Adds business logic to Answer instances before actually accessing
 * the database via DAO.
 * @author Davide Costantini
 * @version 0.3.0
 */
@Component("answerRepository")
public class AnswerRepositoryImpl implements AnswerRepository, UnitOfWork<Answer>  {

	private static final Logger logger = LogManager.getLogger(AnswerRepositoryImpl.class);

    /**
     * The instance of AnswerDAO that will be used to perform actions to the DB
     */
    private final AnswerDAO answerDAO;

	/**
	 * The context of the UnitOfWork
	 */
	private final Map<String, List<Answer>> uofContext;

    @Autowired
    public AnswerRepositoryImpl(AnswerDAO answerDAO) {

        this.answerDAO = answerDAO;
		uofContext = new HashMap<>();
    }

    /**
     * Inserts an instance of Answer in the database
     * @param   answer  an instance of Answer
     */
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
			throw new NotFoundException("{\"response\":\"No Answer with id " + id + " was found.\"}");
		}
    }

	/**
	 * Finds all the answers the user created for a survey.
	 * @param	surveyId	the id of the Survey
	 * @param	userId		the id of the User
	 * @return				an instance of Answer if there is an answer identified by id, null otherwise
	 * @see AnswerRepository#get(int id)
	 */
	@Override
	public Iterable<Answer> getSurveyAnswersForUser(int surveyId, int userId) {

		return answerDAO.findSurveyAnswersForUser(surveyId, userId);
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
     */
    public void remove(int id) throws NotFoundException {

		try {
			answerDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("{\"response\":\"No ClosedEndedAnswer with id " + id + " was found.\"");
		}
    }

	/**
	 * Deletes <code>answer</code> from the database.
	 * @param   answer	the answer to be deleted
	 */
	public void remove(Answer answer)  {

		answerDAO.delete(answer);
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
     */
    public void modify(Answer answer) {

        answerDAO.save(answer);
    }
    
    /**
	 * Registers <code>answer</code> on the specified <code>operation</code>.
	 * @param	answer		the answer to be registered
	 * @param	operation	the operation to be performed on answer
	 */
	private void register(Answer answer, String operation) {

		List<Answer> answerToOperate = uofContext.computeIfAbsent(operation, k -> new ArrayList<>());
		answerToOperate.add(answer);
	}

	/**
	 * Adds <code>answer</code> to the elements to be inserted.
	 * @param	answer	the new Answer
	 * @see UnitOfWork#registerNew
	 */
	@Override
	public void registerNew(Answer answer) {

		logger.debug("Registering Answer with id {} for insert in context.", answer.getId());
		register(answer, UnitOfWork.INSERT);
	}

	/**
	 * Adds <code>answer</code> to the elements to be modified.
	 * @param	answer	the answer that will replace the Answer with the same id
	 * @see UnitOfWork#registerModified
	 */
	@Override
	public void registerModified(Answer answer) {

		logger.debug("Registering Answer with id {} for modify in context.", answer.getId());
		register(answer, UnitOfWork.MODIFY);
	}

	/**
	 * Adds <code>answer</code> to the elements to be deleted.
	 * @param	answer	the answer to be deleted
	 * @see UnitOfWork#registerDeleted
	 */
	@Override
	public void registerDeleted(Answer answer) {

		logger.debug("Registering Answer with id {} for delete in context.", answer.getId());
		register(answer, UnitOfWork.DELETE);
	}

	/**
	 * Commits the changes made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 * @see UnitOfWork#commit
	 */
	@Override
	public void commit(int surveyId, int userId) {

		if (uofContext.size() == 0) {
			return;
		}

		if (uofContext.containsKey(UnitOfWork.INSERT))
			commitInsert(surveyId, userId);
		if (uofContext.containsKey(UnitOfWork.MODIFY))
			commitModify(surveyId, userId);
		if (uofContext.containsKey(UnitOfWork.DELETE))
			commitDelete(surveyId, userId);
	}

	/**
	 * Inserts the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	@Override
	public void commitInsert(int surveyId, int userId) {

		if (uofContext.size() == 0 || !uofContext.containsKey(UnitOfWork.INSERT)) {
			return;
		}

		List<Answer> answerList = uofContext.get(UnitOfWork.INSERT);
		answerList.stream()
				.filter(answer -> answer.getSurvey().getId() == surveyId && answer.getUser().getId() == userId)
				.collect(Collectors.toList())
				.forEach(answer -> {
					add(answer);
					answerList.remove(answer);
				});
		logger.debug("Inserted registered answers for user {} and survey {}.", userId, surveyId);
	}

	/**
	 * Modifies the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	@Override
	public void commitModify(int surveyId, int userId) {

		List<Answer> answerList = uofContext.get(UnitOfWork.MODIFY);

		if (answerList == null)
			return;

		answerList.stream()
				.filter(answer -> answer.getSurvey().getId() == surveyId && answer.getUser().getId() == userId)
				.collect(Collectors.toList())
				.forEach(answer -> {
					modify(answer);
					answerList.remove(answer);
				});
		logger.debug("Modified registered answers for user {} and survey {}.", userId, surveyId);
	}

	/**
	 * Deletes the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	@Override
	public void commitDelete(int surveyId, int userId) {

		List<Answer> answerList = uofContext.get(UnitOfWork.DELETE);

		if (answerList == null)
			return;

		answerList.stream()
				.filter(answer -> answer.getSurvey().getId() == surveyId && answer.getUser().getId() == userId)
				.collect(Collectors.toList())
				.forEach(answer -> {
					answerDAO.delete(answer);
					answerList.remove(answer);
				});
		logger.debug("Deleted registered answers for user {} and survey {}.", userId, surveyId);
	}
}
