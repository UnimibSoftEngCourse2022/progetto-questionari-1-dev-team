package it.unimib.unimibmodules.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.factory.QuestionFactory;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.repository.QuestionRepository;

/**
 * Controller handling HTTP requests related to Question.
 * @author Khalil
 * @version 0.0.1
 */

@RestController
@RequestMapping("/api")
public class QuestionController extends DTOMapping<Question, QuestionDTO>{
	
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
	 */
	@GetMapping(path = "/getQuestion/{id}")
	public ResponseEntity<Question> getQuestion(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Creates a question, with the given text and id
	 * @param	text		the text of the question
	 * @param	questionId
	 * @return				an HTTP response with status 200 if the question has been modified, 500 otherwise
	 */
	@PostMapping(path = "/postQuestion")
	public ResponseEntity<Question> postQuestion(@RequestParam String text, @RequestParam int questionId) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Modifies the question's text associated with the given id.
	 * @param	id		the id of the question
	 * @param	text	the new text of the question
	 * @return			an HTTP response with status 200 if the question has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/patchQuestion")
	public ResponseEntity<Question> patchQuestion(@RequestParam int id, @RequestParam String text) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Deletes the question associated with the given id.
	 * @param   id	the id of the question that will be deleted
	 * @return		an HTTP Response with status 200 if the question has been deleted, 500 otherwise
	 */
	@DeleteMapping(path = "/deleteQuestion/{id}")
	public ResponseEntity<Question> deleteQuestion(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
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
