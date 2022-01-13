package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.repository.QuestionRepository;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller handling HTTP requests related to Question.
 * @author Khalil
 * @version 0.1.0
 */

@RestController
@RequestMapping("/api")
public class QuestionController extends DTOMapping<Question, QuestionDTO>{

    private static final Logger logger = LogManager.getLogger(Question.class);
	
	/**
	 * Instance of QuestionRepository that will be used to access the db.
	 */
	private final QuestionRepository questionRepository;
	
	@Autowired
	public QuestionController(QuestionRepository questionRepository, ModelMapper modelMapper) {
		super(modelMapper);
		this.questionRepository = questionRepository;
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
	 * Creates a question, with the given text and id
	 * @param	questionDTO the serialized object of the question
	 * @return				an HTTP response with status 200 if the question has been modified, 500 otherwise
	 */
	@PostMapping(path = "/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody QuestionDTO questionDTO) {

		Question question = convertToEntity(questionDTO);
		questionRepository.add(question);
		logger.debug("Added Question with id +" + question.getId() + ".");
		return new ResponseEntity<>("Question created.", HttpStatus.CREATED);
	}
	
	/**
	 * Modifies the question's text associated with the given id.
	 * @param	id		the id of the question
	 * @param	text	the new text of the question
	 * @return			an HTTP response with status 200 if the question has been modified, 500 otherwise
	 * @throws  NotFoundException    if no question with identified by <code>id</code> has been found
	 * @throws	EmptyFieldException	if <code>text</code> or <code>urlImage</code> or <code>category</code> are empty
	 */
	@PatchMapping(path = "/modifyQuestion")
	public ResponseEntity<String> modifyQuestion(@RequestParam int id, @RequestParam String text,
												 @RequestParam String urlImage, @RequestParam Category category)
			throws NotFoundException, EmptyFieldException{
		Question question = questionRepository.get(id);
		question.setText(text);
		question.setUrlImage(urlImage);
		question.setCategory(category);
		questionRepository.modify(question);
		logger.debug("Modified Question with id " + id + ".");
		return new ResponseEntity<>("Question modified.", HttpStatus.OK);
	}
	
	/**
	 * Deletes the question associated with the given id.
	 * @param   id	the id of the question that will be deleted
	 * @return		an HTTP Response with status 200 if the question has been deleted, 500 otherwise
	 * @throws	NotFoundException	if no question identified by <code>id</code> has been found
	 */
	@DeleteMapping(path = "/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id) {

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

		return modelMapper.map(question, QuestionDTO.class);
	}

	/**
	 * Converts an instance of QuestionDTO to an instance of Question
	 * @param   questionDTO	an instance of QuestionDTO
	 * @return				an instance of Question, containing the deserialized data of questionDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Question convertToEntity(QuestionDTO questionDTO) {

		return modelMapper.map(questionDTO, Question.class);
	}
	
	
	
	
	
	
	
}
