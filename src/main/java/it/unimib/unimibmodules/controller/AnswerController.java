package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.AnswerDTO;
import it.unimib.unimibmodules.dto.CloseEndedAnswerDTO;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller handling HTTP requests related to Answer and ClosedEndedAnswer.
 * @author Davide Costantini
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api")
public class AnswerController extends DTOMapping<Answer, AnswerDTO> {

	private static final Logger logger = LogManager.getLogger(AnswerController.class);

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final AnswerRepository answerRepository;

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final UserRepository userRepository;

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final SurveyRepository surveyRepository;

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final QuestionRepository questionRepository;

	/**
	 * Instance of AnswerRepository that will be used to access the db.
	 */
	private final CloseEndedAnswerRepository closeEndedAnswerRepository;

	@Autowired
	public AnswerController(AnswerRepository answerRepository, ModelMapper modelMapper, UserRepository userRepository,
							SurveyRepository surveyRepository, QuestionRepository questionRepository,
							CloseEndedAnswerRepository closeEndedAnswerRepository) {

		super(modelMapper);
		this.answerRepository = answerRepository;
		this.userRepository = userRepository;
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
		this.closeEndedAnswerRepository = closeEndedAnswerRepository;

		modelMapper.createTypeMap(AnswerDTO.class, Answer.class)
				.addMapping(AnswerDTO::getAnswerText, Answer::setText);

		modelMapper.createTypeMap(User.class, AnswerDTO.class)
				.addMapping(User::getId, (answerDTO, id) -> answerDTO.getUserDTO().setId(id));
		modelMapper.createTypeMap(Survey.class, AnswerDTO.class)
				.addMapping(Survey::getId, (answerDTO, id) -> answerDTO.getSurveyDTO().setId(id));
		modelMapper.createTypeMap(Question.class, AnswerDTO.class)
				.addMapping(Question::getId, (answerDTO, id) -> answerDTO.getQuestionDTO().setId(id));
	}

	/**
	 * Gets the Answer associated with the given <code>id</code>.
	 * @param	id					the id of the answer
	 * @return						an HTTP response with status 200 and the AnswerDTO if the answer has been found,
	 * 								500 otherwise
	 * @throws	NotFoundException	if no close-ended answer with identified by <code>id</code> has been found
	 */
	@GetMapping(path = "/findAnswer/{id}")
	public ResponseEntity<AnswerDTO> findAnswer(@PathVariable int id) throws NotFoundException {

		Answer answer = answerRepository.get(id);
		logger.debug("Retrieved Answer with id {}.", id);
		return new ResponseEntity<>(convertToDTO(answer), HttpStatus.OK);
	}

	/**
	 * Finds all the Answer the User associated with <code>userId</code> has created for the Survey associated with <code>surveyId</code>.
	 * @param	surveyId			the id of the survey
	 * @param	userId				the id of the user
	 * @return						an HTTP response with status 200 and the AnswerDTO if the answer has been found,
	 * 								500 otherwise
	 * @throws	NotFoundException	if no answer for the survey identified by <code>surveyId</code> and created by the
	 * 								user identified by <code>userId</code> has been found
	 */
	@GetMapping(path = "/findSurveyAnswersForUser")
	public ResponseEntity<List<AnswerDTO>> findSurveyAnswersForUser(@RequestParam int surveyId, @RequestParam int userId)
			throws NotFoundException {

		Iterable<Answer> answer = answerRepository.getSurveyAnswersForUser(surveyId, userId);
		List<AnswerDTO> answerDTOList = convertListToDTO(answer);
		if (answerDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Answers for Survey with id " + surveyId +
					" was found for User with id " + userId + ".\"}");
		logger.debug("Retrieved {} answers for survey with id {} and user with id {}.", answerDTOList.size(),
				surveyId, userId);
		return new ResponseEntity<>(answerDTOList, HttpStatus.OK);
	}

	/**
	 * Creates an Answer.
	 * @param	answerDTO	the serialized object of the answer
	 * @return				an HTTP Response with status 201 if the answer has been created, 500 otherwise
	 */
	@PostMapping(path = "/addAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addAnswer(@RequestBody AnswerDTO answerDTO) throws NotFoundException {

		Answer answer = convertToEntity(answerDTO);
		answerRepository.add(answer);
		logger.debug("Added Answer with id {}.", answer.getId());
		return new ResponseEntity<>("{\"response\":\"Answer creted.\"}", HttpStatus.CREATED);
	}

	/**
	 * Modifies the text of an open-ended question associated with the given <code>id</code>, setting <code>text</code>
	 * as the new answer.
	 * @param   id		the id of the answer that will be modified
	 * @param   text	the new text value
	 * @return			an HTTP response with status 200 if the answer has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/modifyAnswerText")
	public ResponseEntity<String> modifyOpenEndedAnswer(@RequestParam int id, @RequestParam String text)
			throws NotFoundException, EmptyFieldException {

		Answer answer = answerRepository.get(id);
		answer.setText(text);
		answerRepository.modify(answer);
		logger.debug("Modified Answer with id {}.", id);
		return new ResponseEntity<>("Answer modified.", HttpStatus.OK);
	}

	/**
	 * Modifies the answer of a close-ended question associated with the given <code>id</code>, setting text as the new answer.
	 * @param	id						the id of the answer that will be modified
	 * @param	closeEndedAnswerIdList	the new answer
	 * @return							an HTTP response with status 200 if the answer has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/modifyAnswerChoices")
	public ResponseEntity<String> modifyCloseEndedAnswer(@RequestParam int id, @RequestParam List<Integer> closeEndedAnswerIdList)
			throws NotFoundException {

		Answer answer = answerRepository.get(id);
		answer.setCloseEndedAnswers((closeEndedAnswerIdList.stream()
				.map(closeEndedAnswerId -> {
					CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
					closeEndedAnswer.setId(closeEndedAnswerId);
					return closeEndedAnswer;
				}).collect(Collectors.toSet())));
		answerRepository.modify(answer);
		logger.debug("Modified Answer with id {}.", id);
		return new ResponseEntity<>("Answer modified.", HttpStatus.OK);
	}

	/**
	 * Deletes the answer associated with the given <code>id</code>.
	 * @param   id	the id of the answer that will be deleted
	 * @return		an HTTP Response with status 200 if the answer has been deleted, 500 otherwise
	 */
	@DeleteMapping(path = "/deleteAnswer/{id}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int id) throws NotFoundException {

		answerRepository.remove(id);
		logger.debug("Removed Answer with id {}.", id);
		return new ResponseEntity<>("Answer deleted.", HttpStatus.OK);
	}

	/**
	 * Converts an instance of Answer to an instance of AnswerDTO
	 * @param   answer	an instance of Answer
	 * @return			an instance of AnswerDTO, containing the serialized data of answer
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public AnswerDTO convertToDTO(Answer answer) {

		AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
		modelMapper.getTypeMap(User.class, AnswerDTO.class)
				.map(answer.getUser(), answerDTO);
		modelMapper.getTypeMap(Survey.class, AnswerDTO.class)
				.map(answer.getSurvey(), answerDTO);
		modelMapper.getTypeMap(Question.class, AnswerDTO.class)
				.map(answer.getQuestion(), answerDTO);
		Set<CloseEndedAnswerDTO> closeEndedAnswerDTOSet = new HashSet<>();
		for (CloseEndedAnswer closeEndedAnswer : answer.getCloseEndedAnswers()) {
			CloseEndedAnswerDTO closeEndedAnswerDTO = new CloseEndedAnswerDTO();
			closeEndedAnswerDTO.setId(closeEndedAnswer.getId());
			closeEndedAnswerDTOSet.add(closeEndedAnswerDTO);
		}
		answerDTO.setCloseEndedAnswerDTOSet(closeEndedAnswerDTOSet);
		return answerDTO;
	}

	/**
	 * Converts an instance of AnswerDTO to an instance of Answer
	 * @param   answerDTO	an instance of AnswerDTO
	 * @return				an instance of Answer, containing the deserialized data of answerDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Answer convertToEntity(AnswerDTO answerDTO) throws NotFoundException {

		Answer answer = modelMapper.map(answerDTO, Answer.class);
		answer.setUser(userRepository.get(answerDTO.getUserDTO().getId()));
		answer.setSurvey(surveyRepository.get(answerDTO.getSurveyDTO().getId()));
		answer.setQuestion(questionRepository.get(answerDTO.getQuestionDTO().getId()));
		if(answerDTO.getCloseEndedAnswerDTOs() != null) {
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			for (CloseEndedAnswerDTO closeEndedAnswerDTO : answerDTO.getCloseEndedAnswerDTOs()) {
				CloseEndedAnswer closeEndedAnswer = closeEndedAnswerRepository.get(closeEndedAnswerDTO.getId());
				closeEndedAnswerSet.add(closeEndedAnswer);
			}
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
		}
		return answer;
	}
}
