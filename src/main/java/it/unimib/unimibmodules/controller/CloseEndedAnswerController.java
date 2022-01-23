package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.CategoryDTO;
import it.unimib.unimibmodules.dto.CloseEndedAnswerDTO;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Category;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling HTTP requests related to ClosedEndedAnswer.
 * @author Davide Costantini
 * @author Khalil Mohamed Khalil
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api")
public class CloseEndedAnswerController extends DTOMapping<CloseEndedAnswer, CloseEndedAnswerDTO> {

	private static final Logger logger = LogManager.getLogger(CloseEndedAnswerController.class);

	/**
	 * Instance of CloseEndedAnswerRepository that will be used to access the db.
	 */
	private final CloseEndedAnswerRepository closeEndedAnswerRepository;

	/**
	 * Instance of QuestionRepository that will be used to access the db.
	 */
	private final QuestionRepository questionRepository;


	@Autowired
	public CloseEndedAnswerController(CloseEndedAnswerRepository closeEndedAnswerRepository, ModelMapper modelMapper,
									  QuestionRepository questionRepository) {

		super(modelMapper);
		this.closeEndedAnswerRepository = closeEndedAnswerRepository;
		this.questionRepository = questionRepository;

		modelMapper.createTypeMap(CloseEndedAnswer.class, CloseEndedAnswerDTO.class)
				.addMappings(mapper -> {
					mapper.map(CloseEndedAnswer::getId, CloseEndedAnswerDTO::setId);
					mapper.map(CloseEndedAnswer::getText, CloseEndedAnswerDTO::setText);
				});

		modelMapper.createTypeMap(CloseEndedAnswerDTO.class, CloseEndedAnswer.class)
				.addMappings(mapper -> {
					mapper.map(CloseEndedAnswerDTO::getId, CloseEndedAnswer::setId);
					mapper.map(CloseEndedAnswerDTO::getText, CloseEndedAnswer::setText);
				});

		modelMapper.createTypeMap(CloseEndedAnswer.class, CloseEndedAnswerDTO.class)
				.addMappings(mapper -> {
					mapper.map(CloseEndedAnswer::getId, CloseEndedAnswerDTO::setId);
					mapper.map(CloseEndedAnswer::getText, CloseEndedAnswerDTO::setText);
				});

		modelMapper.createTypeMap(Question.class, CloseEndedAnswerDTO.class)
				.addMapping(Question::getId, (closeEndedAnswerDTO, id) -> closeEndedAnswerDTO.getQuestionDTO().setId(id));
	}

	/**
	 * Gets the Answer associated with the given id.
	 * @param	id					the id of the close-ended answer
	 * @return						an HTTP response with status 200 and the CloseEndedAnswerDTO if the close-ended
	 * 								answer has been found, 500 otherwise
	 * @throws	NotFoundException	if no close-ended answer with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/findCloseEndedAnswer/{id}")
	public ResponseEntity<CloseEndedAnswerDTO> findCloseEndedAnswer(@PathVariable int id) throws NotFoundException {

		CloseEndedAnswer closeEndedAnswer = closeEndedAnswerRepository.get(id);
		logger.debug("Retrieved CloseEndedAnswer with id {}.", id);
		return new ResponseEntity<>(convertToDTO(closeEndedAnswer), HttpStatus.OK);
	}

	/**
	 * Creates an answer to a close-ended question.
	 * @param	closeEndedAnswerDTO	the serialized object of the close-ended answer
	 * @return						an HTTP response with status 201 if the new close-ended answer has been created,
	 * 								500 otherwise
	 * @throws	NotFoundException	when one of the queries of convertToEntity fails.
	 */
	@PostMapping(path = "/addCloseEndedAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postCloseEndedAnswer(@RequestBody CloseEndedAnswerDTO closeEndedAnswerDTO)
			throws NotFoundException {

		CloseEndedAnswer closeEndedAnswer = closeEndedAnswer = convertToEntity(closeEndedAnswerDTO);
		closeEndedAnswerRepository.add(closeEndedAnswer);
		logger.debug("Added CloseEndedAnswer with id {}.", closeEndedAnswer.getId());
		return new ResponseEntity<>("{\"response\":\"CloseEndedAnswer creted.\"}", HttpStatus.CREATED);
	}

	/**
	 * Modifies the answer of a close-ended question associated with the given id, setting text as the answer.
	 * @param	closeEndedAnswerDTO	the serialized object of the close-ended answer
	 * 								500 otherwise
	 * @throws	NotFoundException	if no close-ended answer with identified by <code>id</code> has been found
	 * @throws	EmptyFieldException	if <code>text</code> is empty
	 */
	@PatchMapping(path = "/modifyCloseEndedAnswer")
	public ResponseEntity<String> modifyCloseEndedAnswer(@RequestBody CloseEndedAnswerDTO closeEndedAnswerDTO)
			throws NotFoundException, EmptyFieldException {

		CloseEndedAnswer closeEndedAnswer = convertToEntity(closeEndedAnswerDTO);
		closeEndedAnswerRepository.modify(closeEndedAnswer);
		logger.debug("Modified CloseEndedAnswer with id {}.", closeEndedAnswer.getId());
		return new ResponseEntity<>("CloseEndedAnswer modified.", HttpStatus.OK);
	}

	/**
	 * Deletes the close-ended answer associated with the given id.
	 * @param   id					the id of the close-ended answer that will be deleted
	 * @return						an HTTP Response with status 200 if the close-ended answer has been deleted,
	 * 								500 otherwise
	 * @throws	NotFoundException	if no close-ended answer identified by <code>id</code> has been found
	 */
	@DeleteMapping(path = "/deleteCloseEndedAnswer/{id}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int id) throws NotFoundException {

		closeEndedAnswerRepository.remove(id);
		logger.debug("Removed CloseEndedAnswer with id {}.", id);
		return new ResponseEntity<>("CloseEndedAnswer deleted.", HttpStatus.OK);
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
		modelMapper.getTypeMap(Question.class, CloseEndedAnswerDTO.class)
				.map(closeEndedAnswer.getQuestion(), closeEndedAnswerDTO);
		return closeEndedAnswerDTO;
	}

	/**
	 * Converts an instance of CloseEndedAnswer to an instance of CloseEndedAnswerDTO
	 * @param   closeEndedAnswerDTO	an instance of CloseEndedAnswerDTO
	 * @return						an instance of CloseEndedAnswer, containing the deserialized data of closeEndedAnswerDTO
	 * @throws	NotFoundException	when one of the queries fails.
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public CloseEndedAnswer convertToEntity(CloseEndedAnswerDTO closeEndedAnswerDTO) throws NotFoundException {

		CloseEndedAnswer closeEndedAnswer = modelMapper.map(closeEndedAnswerDTO, CloseEndedAnswer.class);
		closeEndedAnswer.setQuestion(questionRepository.get(closeEndedAnswerDTO.getQuestionDTO().getId()));
		return closeEndedAnswer;
	}
}