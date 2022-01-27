package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller handling HTTP requests related to Question.
 * @author Khalil
 * @version 0.3.0
 */

@RestController
@RequestMapping("/api")
public class QuestionController extends DTOListMapping<Question, QuestionDTO>{

    private static final Logger logger = LogManager.getLogger(QuestionController.class);
	
	/**
	 * Instance of QuestionRepository that will be used to access the db.
	 */
	private final QuestionRepository questionRepository;

	/**
	 * Instance of UserRepository that will be used to access the db.
	 */
	private final UserRepository userRepository;

	/**
	 * Instance of CategoryRepository that will be used to access the db.
	 */
	private final CategoryRepository categoryRepository;

	@Autowired
	public QuestionController(QuestionRepository questionRepository, UserRepository userRepository, ModelMapper modelMapper,
							  CategoryRepository categoryRepository) {

		super(modelMapper);
		this.questionRepository = questionRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;

		modelMapper.createTypeMap(Question.class, QuestionDTO.class)
				.addMappings(mapper -> {
					mapper.map(Question::getId, QuestionDTO::setId);
					mapper.map(Question::getUrlImage, QuestionDTO::setUrlImage);
					mapper.map(Question::getText, QuestionDTO::setText);
					mapper.map(Question::getCategory, QuestionDTO::setCategory);
					mapper.map(Question::getCloseEndedAnswerSet, QuestionDTO::setCloseEndedAnswerDTOSet);
					mapper.map(Question::getQuestionType, QuestionDTO::setQuestionType);
					mapper.map(Question::getUser, QuestionDTO::setUser);
				});

		modelMapper.createTypeMap(QuestionDTO.class, Question.class)
				.addMappings(mapper -> {
					mapper.map(QuestionDTO::getId, Question::setId);
					mapper.map(QuestionDTO::getUrlImage, Question::setUrlImage);
					mapper.map(QuestionDTO::getText, Question::setText);
					mapper.map(QuestionDTO::getQuestionType, Question::setQuestionType);
				});

        modelMapper.createTypeMap(User.class, QuestionDTO.class)
                .addMapping(User::getId, (questionDTO, id) -> questionDTO.getUser().setId(id));

	}
	
	/**
	 * Gets the Question associated with the given id.
	 * @param	id	the id of the question
	 * @return		an HTTP response with status 200 and the QuestionDTO if the question has been found, 500 otherwise
	 * @throws  NotFoundException	if 404 no question with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/getQuestion/{id}")
	public ResponseEntity<QuestionDTO> getQuestion(@PathVariable int id) throws NotFoundException{

        Question question = questionRepository.get(id);
		logger.debug("Retrieved Question with id "+ id + ".");
		return new ResponseEntity<>(convertToDTO(question), HttpStatus.OK);
	}

	/**
	 * Gets all the questions
	 * @return		an HTTP response with status 200, 500 otherwise
	 * @throws NotFoundException if 404 no question has been found
	 */
	@GetMapping(path = "/getQuestion")
	public ResponseEntity<List<QuestionDTO>> getQuestions() throws NotFoundException{
		Iterable<Question> questionList = questionRepository.getAll();
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		logger.debug("Retrieved all the questions.");
    return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
   }

	/**
	 * Gets all the questions of the user
	 * @return		an HTTP response with status 200, 500 otherwise
	 * @throws NotFoundException 
	 */
	@GetMapping(path = "/getQuestionByUser/{id}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByUser(@PathVariable int id) throws NotFoundException{
		Iterable<Question> questionList = questionRepository.getByUser(id);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No questions for user with id" + id + " was found.\"}");
		logger.debug("Retrieved all the questions of the user "+id + ".");
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}
	
	/**
	 * Gets all the questions of the user with Lazy Loading
	 * @param id user id
	 * @param offset 
	 * @param limit
	 * @return		an HTTP response with status 200, 500 otherwise
	 * @throws NotFoundException 
	 */
	@GetMapping(path = "/getQuestionByUserLazy")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByUserLazy(@RequestParam int id,  @RequestParam int offset , @RequestParam int limit) throws NotFoundException{
		Iterable<Question> questionList = questionRepository.getByUserLazy(id, offset , limit);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No questions for user with id " + id + " was found.\"}");
		logger.debug("Retrieved all the questions of the user "+id + ".");
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}

	/**
	 * Gets all the questions associated with the given id of category
	 * @return		an HTTP response with status 200, 500 otherwise
	 */
	@GetMapping(path = "/getQuestionByCategory/{id}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByCategory(@PathVariable int id) throws NotFoundException{
		Iterable<Question> questionList = questionRepository.getByCategory(id);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Question with category" + id + " was found.\"}");
		logger.debug("Retrieved all the questions of the user "+id + ".");
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}
	
	
	/**
	 * Gets all the questions associated with the given id of category with Lazy Loading
	 * @param offset 
	 * @param limit
	 * @return		an HTTP response with status 200, 500 otherwise
	 */
	@GetMapping(path = "/getQuestionByCategoryLazy")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByCategoryLazy(@RequestParam int id,  @RequestParam int offset , @RequestParam int limit) throws NotFoundException{
		Iterable<Question> questionList = questionRepository.getByCategoryLazy(id, offset, limit);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Question with category" + id + " was found.\"}");
		logger.debug("Retrieved all the questions of the user "+id + ".");
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}

 	/**
	 * Gets the question in the database where text is contained in the text of the question
	 * @param	text	the text of the question to be found
	 * @return		an HTTP response with status 200 and the QuestionDTO if the question has been found, 500 otherwise
	 * @throws  NotFoundException	if 404 no question with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/findQuestionsByText/{text}")
	public ResponseEntity<List<QuestionDTO>> findQuestionsByText(@PathVariable String text) throws NotFoundException {

		Iterable<Question> questionList = questionRepository.getByText(text);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Question with " + text + " was found.\"}");
		logger.debug("Retrieved {} Questions containing the text {}.", questionDTOList.size(), text);
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}
	
	
	/**
	 * Gets the question in the database where text is contained in the text of the question with Lazy Loading
	 * @param	text	the text of the question to be found
	 * @param offset 
	 * @param limit
	 * @return		an HTTP response with status 200 and the QuestionDTO if the question has been found, 500 otherwise
	 * @throws  NotFoundException	if 404 no question with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/findQuestionsByTextLazy")
	public ResponseEntity<List<QuestionDTO>> findQuestionsByTextLazy(@RequestParam String text , @RequestParam int offset , @RequestParam int limit) throws NotFoundException {

		Iterable<Question> questionList = questionRepository.getByTextLazy(text, offset, limit);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Question with " + text + " was found.\"}");
		logger.debug("Retrieved {} Questions containing the text {}.", questionDTOList.size(), text);
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}

	/**
	 * Gets the Question associated with the given id.
	 * @param	id	the id of the question
	 * @return		an HTTP response with status 200 and the QuestionDTO if the question has been found, 500 otherwise
	 * @throws  NotFoundException	if 404 no question with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/findQuestionForSurvey/{id}")
	public ResponseEntity<List<QuestionDTO>> findQuestionsForSurvey(@PathVariable int id) throws NotFoundException {
		Iterable<Question> questionList = questionRepository.getBySurveyId(id);
		List<QuestionDTO> questionDTOList = convertListToDTO(questionList);
		if (questionDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Question for Survey with id " + id + " was found.\"}");
		logger.debug("Retrieved {} questions for survey with id {}.", questionDTOList.size(), id);
		return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
	}
	
	/**
	 * Creates a question, with the given text and id
	 * @param	questionDTO the serialized object of the question
	 * @return				an HTTP response with status 200 if the question has been modified, 500 otherwise
	 * @throws	NotFoundException	if no user or category identified by <code>id</code> has been found
	 */
	@PostMapping(path = "/addQuestion")
	public ResponseEntity<Integer> addQuestion(@RequestBody QuestionDTO questionDTO) throws NotFoundException {

		Question question = convertToEntity(questionDTO);
		questionRepository.add(question);
		logger.debug("Added Question with id +" + question.getId() + ".");
		return new ResponseEntity<>(question.getId(), HttpStatus.CREATED);
	}
	
	/**
	 * Modifies the question's text associated with the given id.
	 * @param	questionDTO the serialized object of the question
	 * @return			an HTTP response with status 200 if the question has been modified, 500 otherwise
	 * @throws  NotFoundException    if no question with identified by <code>id</code> has been found
	 */
	@PatchMapping(path = "/modifyQuestion")
	public ResponseEntity<String> modifyQuestion(@RequestBody QuestionDTO questionDTO)
			throws NotFoundException {
		Question question = convertToEntity(questionDTO);
		questionRepository.modify(question);
		logger.debug("Modified Question with id " + question.getId() + ".");
		return new ResponseEntity<>("Question modified.", HttpStatus.OK);
	}
	
	/**
	 * Deletes the question associated with the given id.
	 * @param   id	the id of the question that will be deleted
	 * @return		an HTTP Response with status 200 if the question has been deleted, 500 otherwise
	 * @throws	NotFoundException	if no question identified by <code>id</code> has been found
	 */
	@DeleteMapping(path = "/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id) throws NotFoundException {

		questionRepository.remove(id);
		logger.debug("Removed Question with id " + id + ".");
		return new ResponseEntity<>("Question deleted", HttpStatus.OK);
	}
	
	/**
	 * Converts an instance of Question to an instance of questionDTO
	 * @param   question	an instance of Question
	 * @return			    an instance of QuestionDTO, containing the serialized data of question
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public QuestionDTO convertToDTO(Question question) {

		QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
		modelMapper.getTypeMap(User.class, QuestionDTO.class)
				.map(question.getUser(), questionDTO);
		return questionDTO;
	}

	/**
	 * Converts a list of Question to a list of questionDTO
	 * @param   questions	the list of Questions
	 * @return			    a list of QuestionDTO, containing the serialized data of questions
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public List<QuestionDTO> convertListToDTO(Iterable<Question> questions) {

		List<QuestionDTO> questionList = new ArrayList<>();
		for (Question question : questions)
			questionList.add(convertToDTO(question));

		return questionList;
	}

	/**
	 * Converts an instance of QuestionDTO to an instance of Question
	 * @param   questionDTO	an instance of QuestionDTO
	 * @return				an instance of Question, containing the deserialized data of questionDTO
	 * @throws	NotFoundException	if no user or category identified by <code>id</code> has been found
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Question convertToEntity(QuestionDTO questionDTO) throws NotFoundException {

		Question question = modelMapper.map(questionDTO, Question.class);
		question.setUser(userRepository.get(questionDTO.getUser().getId()));
		question.setCategory(categoryRepository.get(questionDTO.getCategory().getId()));
		return question;
	}
}
