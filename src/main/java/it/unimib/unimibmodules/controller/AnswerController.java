package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.AnswerDTO;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.repository.AnswerRepository;
import it.unimib.unimibmodules.unitofwork.UnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling HTTP requests related to Answer and ClosedEndedAnswer.
 * @author Davide Costantini
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api")
public class AnswerController extends DTOMapping<Answer, AnswerDTO> implements UnitOfWork<Answer> {

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final AnswerRepository answerRepository;

	/**
	 * The instance of modelMapper that will be used to convert Answer to AnswerDTO and vice versa.
	 */
	private final ModelMapper modelMapper;

	@Autowired
	public AnswerController(AnswerRepository answerRepository, ModelMapper modelMapper) {

		this.answerRepository = answerRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Gets the Answer associated with the given id.
	 * @param	id	the id of the answer
	 * @return		an HTTP response with status 200 and the AnswerDTO if the answer has been found, 500 otherwise
	 */
	@GetMapping(path = "/getAnswer/{id}")
	public ResponseEntity<Answer> getAnswer(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Creates an answer to a close-ended question where text is the answer.
	 * @param	text	the text of the answer
	 * @return			an HTTP response with status 200 if the answer has been modified, 500 otherwise
	 */
	@PostMapping(path = "/postCloseEndedAnswer")
	public ResponseEntity<Answer> postCloseEndedAnswer(@RequestParam String text) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Creates an answer to an open-ended question, where text is the answer written by the user.
	 * @param   text	the answer written by the user
	 * @return			an HTTP Response with status 200 if the answer has been created, 500 otherwise
	 */
	@PostMapping(path = "/postOpenEndedAnswer")
	public ResponseEntity<Answer> postOpenEndedAnswer(@RequestParam String text) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Modifies the answer of a close-ended question associated with the given id, setting text as the answer.
	 * @param	id		the id of the answer
	 * @param	text	the new text of the answer
	 * @return			an HTTP response with status 200 if the answer has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/patchCloseEndedAnswer")
	public ResponseEntity<Answer> patchCloseEndedAnswer(@RequestParam int id, @RequestParam String text) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Modifies the answer of an open-ended question associated with the given id, setting text as the new answer.
	 * @param   id		the id of the answer that will be modified
	 * @param   text	the new answer
	 * @return			an HTTP response with status 200 if the answer has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/patchOpenEndedAnswer")
	public ResponseEntity<Answer> patchOpenEndedAnswer(@RequestParam int id, @RequestParam String text) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Deletes the answer associated with the given id.
	 * @param   id	the id of the answer that will be deleted
	 * @return		an HTTP Response with status 200 if the answer has been deleted, 500 otherwise
	 */
	@DeleteMapping(path = "/deleteAnswer/{id}")
	public ResponseEntity<Answer> deleteAnswer(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Converts an instance of Answer to an instance of AnswerDTO
	 * @param   answer	an instance of Answer
	 * @return			an instance of AnswerDTO, containing the serialized data of answer
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public AnswerDTO convertToDTO(Answer answer) {

		return modelMapper.map(answer, AnswerDTO.class);
	}

	/**
	 * Converts an instance of AnswerDTO to an instance of Answer
	 * @param   answerDTO	an instance of AnswerDTO
	 * @return				an instance of Answer, containing the deserialized data of answerDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Answer convertToEntity(AnswerDTO answerDTO) {

		return modelMapper.map(answerDTO, Answer.class);
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
