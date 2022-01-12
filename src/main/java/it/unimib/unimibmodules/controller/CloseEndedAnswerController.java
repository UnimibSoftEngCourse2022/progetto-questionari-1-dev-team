package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.CloseEndedAnswerDTO;
import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.repository.CloseEndedAnswerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling HTTP requests related to ClosedEndedAnswer.
 * @author Davide Costantini
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api")
public class CloseEndedAnswerController extends DTOMapping<CloseEndedAnswer, CloseEndedAnswerDTO> {

	private static final Logger logger = LogManager.getLogger(CloseEndedAnswer.class);

	/**
	 * Instance of CloseEndedAnswerRepository that will be used to access the db.
	 */
	private final CloseEndedAnswerRepository closeEndedAnswerRepository;

	@Autowired
	public CloseEndedAnswerController(CloseEndedAnswerRepository closeEndedAnswerRepository, ModelMapper modelMapper) {

		super(modelMapper);
		this.closeEndedAnswerRepository = closeEndedAnswerRepository;

		modelMapper.addMappings(new PropertyMap<Question, QuestionDTO>() {

			@Override
			protected void configure() {

				skip(destination.getCategory());
				skip(destination.getAnswer());
				skip(destination.getCloseEndedAnswerSet());
				skip(destination.getUser());
				skip(destination.getSurvey());
			}
		});
	}

	/**
	 * Gets the Answer associated with the given id.
	 * @param	id					the id of the close-ended answer
	 * @return						an HTTP response with status 200 and the CloseEndedAnswerDTO if the close-ended
	 * 								answer has been found, 500 otherwise
	 * @throws	NotFoundException	if 404 no close-ended answer with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/getCloseEndedAnswer/{id}")
	public ResponseEntity<CloseEndedAnswerDTO> getAnswer(@PathVariable int id) throws NotFoundException {

		CloseEndedAnswer closeEndedAnswer = closeEndedAnswerRepository.get(id);
		logger.debug("Retrieved CloseEndedAnswer with id " + id + ".");
		return new ResponseEntity<>(convertToDTO(closeEndedAnswer), HttpStatus.OK);
	}

	/**
	 * Creates an answer to a close-ended question.
	 * @param	closeEndedAnswerDTO	the serialized object of the close-ended answer
	 * @return						an HTTP response with status 200 if the new close-ended answer has been created,
	 * 								500 otherwise
	 */
	@PostMapping(path = "/addCloseEndedAnswer")
	public ResponseEntity<String> postCloseEndedAnswer(@RequestBody CloseEndedAnswerDTO closeEndedAnswerDTO) {

		CloseEndedAnswer closeEndedAnswer = convertToEntity(closeEndedAnswerDTO);
		closeEndedAnswerRepository.add(closeEndedAnswer);
		logger.debug("Added CloseEndedAnswer with id " + closeEndedAnswer.getId() + ".");
		return new ResponseEntity<>("CloseEndedAnswer creted.", HttpStatus.CREATED);
	}

	/**
	 * Modifies the answer of a close-ended question associated with the given id, setting text as the answer.
	 * @param	id					the id of the close-ended answer to be modified
	 * @param	text				the new text of the close-ended answer
	 * @return						an HTTP response with status 200 if the close-ended answer has been modified,
	 * 								500 otherwise
	 * @throws	NotFoundException	if no close-ended answer with identified by <code>id</code> has been found
	 * @throws	EmptyFieldException	if <code>text</code> is empty
	 */
	@PatchMapping(path = "/modifyCloseEndedAnswer")
	public ResponseEntity<String> modifyCloseEndedAnswer(@RequestParam int id, @RequestParam String text)
			throws NotFoundException, EmptyFieldException {

		CloseEndedAnswer closeEndedAnswer = closeEndedAnswerRepository.get(id);
		closeEndedAnswer.setText(text);
		closeEndedAnswerRepository.modify(closeEndedAnswer);
		logger.debug("Modified CloseEndedAnswer with id " + id + ".");
		return new ResponseEntity<>("CloseEndedAnswer modified.", HttpStatus.OK);
	}

	/**
	 * Deletes the close-ended answer associated with the given id.
	 * @param   id					the id of the close-ended answer that will be deleted
	 * @return						an HTTP Response with status 200 if the close-ended answer has been deleted,
	 * 								500 otherwise
	 * @throws	NotFoundException	if no close-ended answer with identified by <code>id</code> has been found
	 */
	@DeleteMapping(path = "/deleteCloseEndedAnswer/{id}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int id) throws NotFoundException {

		closeEndedAnswerRepository.remove(id);
		logger.debug("Removed CloseEndedAnswer with id " + id + ".");
		return new ResponseEntity<>("CloseEndedAnswer deleted", HttpStatus.I_AM_A_TEAPOT);
	}

	/**
	 * Converts an instance of CloseEndedAnswerDTO to an instance of AnswerDTO
	 * @param   closeEndedAnswer	an instance of CloseEndedAnswer
	 * @return						an instance of CloseEndedAnswerDTO, containing the serialized data of closeEndedAnswer
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public CloseEndedAnswerDTO convertToDTO(CloseEndedAnswer closeEndedAnswer) {

		CloseEndedAnswerDTO closeEndedAnswerDTO = modelMapper.map(closeEndedAnswer, CloseEndedAnswerDTO.class);
		closeEndedAnswerDTO.setQuestionDTO(modelMapper.map(closeEndedAnswer.getQuestion(), QuestionDTO.class));
		return closeEndedAnswerDTO;
	}

	/**
	 * Converts an instance of CloseEndedAnswer to an instance of CloseEndedAnswerDTO
	 * @param   closeEndedAnswerDTO	an instance of CloseEndedAnswerDTO
	 * @return						an instance of CloseEndedAnswer, containing the deserialized data of closeEndedAnswerDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public CloseEndedAnswer convertToEntity(CloseEndedAnswerDTO closeEndedAnswerDTO) {

		return modelMapper.map(closeEndedAnswerDTO, CloseEndedAnswer.class);
	}
}
