package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.SurveyDTO;
import it.unimib.unimibmodules.dto.SurveyQuestionsDTO;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;
import it.unimib.unimibmodules.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller handling HTTP requests related to Survey.
 * 
 * @author Luca Milazzo
 * @version 0.4.0
 */
@RestController
@RequestMapping("/api")
public class SurveyController extends DTOMapping<Survey, SurveyDTO> {
	/**
	 * Instance of SurveyRepository. It's used to access the Repository layer.
	 */
	private final SurveyRepository surveyRepository;

	/**
	 * Instance of UserRepository that will be used to access the db.
	 */
	private final UserRepository userRepository;

	private static final Logger logger = LogManager.getLogger(SurveyController.class);
	
	private final String retrivedAllSurveys = "Retreived all Surveys";
	private final String retrivedNSurveys = "Retrieved {} surveys.";

	@Autowired

	public SurveyController(UserRepository userRepository, SurveyRepository surveyRepository,

			ModelMapper modelMapper) {
		super(modelMapper);
		this.surveyRepository = surveyRepository;
		this.userRepository = userRepository;

		modelMapper.createTypeMap(Survey.class, SurveyDTO.class).addMappings(mapper -> {
			mapper.map(Survey::getId, SurveyDTO::setId);
			mapper.map(Survey::getName, SurveyDTO::setSurveyName);
			mapper.skip(SurveyDTO::setQuestions);
		});

		modelMapper.createTypeMap(SurveyQuestions.class, SurveyQuestionsDTO.class).addMappings(mapper -> {
			mapper.map(SurveyQuestions::getId, SurveyQuestionsDTO::setId);
			mapper.map(SurveyQuestions::getQuestion, SurveyQuestionsDTO::setQuestionDTO);
		});

		modelMapper.createTypeMap(SurveyQuestionsDTO.class, SurveyQuestions.class).addMappings(mapper -> {
			mapper.map(SurveyQuestionsDTO::getQuestionDTO, SurveyQuestions::setQuestion);
			mapper.map(SurveyQuestionsDTO::getSurveyDTO, SurveyQuestions::setSurvey);
		});

		modelMapper.createTypeMap(SurveyDTO.class, Survey.class).addMappings(mapper -> 
			mapper.map(SurveyDTO::getId, Survey::setId)
		);

		modelMapper.createTypeMap(User.class, SurveyDTO.class).addMappings(mapper -> {
			mapper.map(User::getId, (surveyDTO, id) -> surveyDTO.getUserDTO().setId(id));
			mapper.map(User::getUsername, (surveyDTO, username) -> surveyDTO.getUserDTO().setUsername(username));
		});

	}

	/**
	 * Finds the survey associated with the given id.
	 * 
	 * @param id the id of the Survey
	 * @return an HTTP response with status 200 and the SurveyDTO if the survey has
	 *         been found
	 * @throws NotFoundException if the id doesn't exists
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurvey")
	public ResponseEntity<SurveyDTO> findSurvey(@RequestParam(name = "id") int id) throws NotFoundException {
		Survey survey = surveyRepository.get(id);
		logger.debug("Retreived Survey with id: {}.", id);
		return new ResponseEntity<>(convertToDTO(survey), HttpStatus.OK);
	}

	/**
	 * Finds the survey associated with the given id without its question list.
	 * 
	 * @param id the id of the Survey
	 * @return an HTTP response with status 200 and the SurveyDTO if the survey has
	 *         been found
	 * @throws NotFoundException if the id doesn't exists
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping(path = "/findSurveyNoQuestion/{id}")
	public ResponseEntity<SurveyDTO> findSurveyNoQuestion(@PathVariable int id) throws NotFoundException {
		Survey survey = surveyRepository.get(id);
		logger.debug("Retreived Survey with id: {}.", id);
		return new ResponseEntity<>(convertToDTOAndSkipQuestions(survey), HttpStatus.OK);
	}
	

	/**
	 * Gets the survey in the database where text is contained in the name of the
	 * survey.
	 * 
	 * @param text the text to be found in the name of the survey
	 * @return an HTTP response with status 200 and the SurveyDTOs
	 * @throws NotFoundException if no surveys have been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurveyByText")
	public ResponseEntity<List<SurveyDTO>> findSurveyByText(@RequestParam(name = "text") String text)
			throws NotFoundException {

		Iterable<Survey> surveyList = surveyRepository.getByText(text);
		List<SurveyDTO> surveyDTOList = new ArrayList<>();
		for (Survey survey : surveyList) {
			surveyDTOList.add(convertToDTO(survey));
		}
		if (surveyDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Surveys were found with " + text + "\"}");
		logger.debug("Retrieved {} Surveys containing the text", surveyDTOList.size());
		return new ResponseEntity<>(surveyDTOList, HttpStatus.OK);
	}

	/**
	 * Gets the surveys in the database, without their questions, where text is
	 * contained in the name of the survey or text is its ID.
	 * 
	 * @param text the text to be found in the name of the survey
	 * @return an HTTP response with status 200 and the SurveyDTOs
	 * @throws NotFoundException if no surveys have been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurveyByTextNoQuestion")
	public ResponseEntity<List<SurveyDTO>> findSurveyByTextNoQuestions(@RequestParam(name = "text") String text)
			throws NotFoundException {

		Iterable<Survey> surveyList = surveyRepository.getByText(text);
		List<SurveyDTO> surveyDTOList = new ArrayList<>();
		for (Survey survey : surveyList) {
			surveyDTOList.add(convertToDTOAndSkipQuestions(survey));
		}
		if (surveyDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Surveys were found with " + text + "\"}");
		logger.debug("Retrieved  {} surveys containing the text." , surveyDTOList.size());
		return new ResponseEntity<>(surveyDTOList, HttpStatus.OK);
	}

	/**
	 * Gets the surveys in the database, without their questions, where text is
	 * contained in the name of the survey or text is its ID with Lazy Loading
	 * 
	 * @param text the text to be found in the name of the survey
	 * @param offset offset for lazy loading
	 * @param limit to limit the result length
	 * @return an HTTP response with status 200 and the SurveyDTOs
	 * @throws NotFoundException if no surveys have been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurveyByTextNoQuestionLazy")
	public ResponseEntity<List<SurveyDTO>> findSurveyByTextNoQuestionsLazy(@RequestParam(name = "text") String text,
			@RequestParam int offset, @RequestParam int limit) throws NotFoundException {

		Iterable<Survey> surveyList = surveyRepository.getByTextLazy(text, offset, limit);
		List<SurveyDTO> surveyDTOList = new ArrayList<>();
		for (Survey survey : surveyList) {
			surveyDTOList.add(convertToDTOAndSkipQuestions(survey));
		}
		if (surveyDTOList.isEmpty())
			throw new NotFoundException("{\"response\":\"No Surveys were found with " + text + "\"}");
		logger.debug("Retrieved  {} surveys containing the text." , surveyDTOList.size());
		return new ResponseEntity<>(surveyDTOList, HttpStatus.OK);
	}

	/**
	 * Finds all surveys.
	 * 
	 * @return an HTTP response with status 200 if one survey exists at least.
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findAllSurveys")
	public ResponseEntity<List<SurveyDTO>> findAllSurveys() throws NotFoundException {

		Iterable<Survey> surveys = surveyRepository.getAll();
		logger.debug(retrivedAllSurveys);
		List<SurveyDTO> surveysDTO = new ArrayList<>();
		for (Survey survey : surveys) {
			surveysDTO.add(convertToDTO(survey));
		}
		logger.debug(retrivedNSurveys, surveysDTO.size());
		return new ResponseEntity<>(surveysDTO, HttpStatus.OK);
	}

	/**
	 * Finds all surveys without their questions.
	 * 
	 * @return an HTTP response with status 200 if one survey exists at least.
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findAllSurveysNoQuestion")
	public ResponseEntity<List<SurveyDTO>> findAllSurveysNoQuestion() throws NotFoundException {

		Iterable<Survey> surveys = surveyRepository.getAll();
		logger.debug(retrivedAllSurveys);
		List<SurveyDTO> surveysDTO = new ArrayList<>();
		for (Survey survey : surveys) {
			surveysDTO.add(convertToDTOAndSkipQuestions(survey));
		}
		logger.debug(retrivedNSurveys, surveysDTO.size());
		return new ResponseEntity<>(surveysDTO, HttpStatus.OK);
	}
	
	/**
	 * Finds all surveys without their questions.
	 * 
	 * @return an HTTP response with status 200 if one survey exists at least.
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findAllSurveysNoQuestionLazy")
	public ResponseEntity<List<SurveyDTO>> findAllSurveysNoQuestionLazy(@RequestParam int offset, 
			@RequestParam int limit) throws NotFoundException {

		Iterable<Survey> surveys = surveyRepository.getAllLazy(offset, limit);
		logger.debug(retrivedAllSurveys);
		List<SurveyDTO> surveysDTO = new ArrayList<>();
		for (Survey survey : surveys) {
			surveysDTO.add(convertToDTOAndSkipQuestions(survey));
		}
		logger.debug(retrivedNSurveys, surveysDTO.size());
		return new ResponseEntity<>(surveysDTO, HttpStatus.OK);
	}

	/**
	 * Creates a survey, with the given name and request's date-time, that is
	 * associated to the given user id
	 * 
	 * @param surveyDTO the serialized version of a Survey object
	 * @return an HTTP response with status 201 if the survey has been added
	 * @throws FormatException
	 * @throws NotFoundException
	 * @throws EmptyFieldException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.EmptyFieldException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@PostMapping(path = "/addSurvey", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSurvey(@RequestBody SurveyDTO surveyDTO)
			throws FormatException, NotFoundException, EmptyFieldException {
		Survey survey = convertToEntity(surveyDTO);
		surveyRepository.add(survey);
		logger.debug("Added Survey with id: {}.", survey.getId());
		return new ResponseEntity<>("{\"response\":\"Survey creted.\"}", HttpStatus.CREATED);
	}

	/**
	 * Modifies the survey's name associated with the given surveyDTO.
	 * 
	 * @param surveyDTO the serialized version of a Survey object
	 * @return an HTTP response with status 200 if the survey has been updated
	 * @throws FormatException
	 * @throws NotFoundException
	 * @throws EmptyFieldException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.EmptyFieldException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@PatchMapping(path = "/modifySurvey", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> modifySurvey(@RequestBody SurveyDTO surveyDTO)
			throws FormatException, NotFoundException, EmptyFieldException {

		Survey survey = convertToEntity(surveyDTO);
		Set<SurveyQuestions> surveyQuestions = survey.getSurveyQuestions();
		surveyRepository.modifyQuestions(surveyQuestions, survey.getId());
		surveyRepository.modifyName(survey.getName(), survey.getId());
		return new ResponseEntity<>("{\"response\":\"Survey modified.\"}", HttpStatus.OK);
	}

	/**
	 * Deletes the survey associated with the given id.
	 * 
	 * @param id the id of the survey to delete
	 * @return an HTTP Response with status 200 if the survey has been deleted
	 * @throws NotFoundException
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@DeleteMapping(path = "/deleteSurvey/{id}")
	public ResponseEntity<String> deleteSurvey(@PathVariable int id) throws FormatException {
		surveyRepository.remove(id);
		logger.debug("Removed Survey with id: {}." , id);
		return new ResponseEntity<>("{\"response\":\"Survey deleted.\"}", HttpStatus.OK);
	}

	/**
	 * Conversion of an instance of Survey to an instance of SurveyDTO
	 * 
	 * @param survey an instance of Survey
	 * @return an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public SurveyDTO convertToDTO(Survey survey) {

		SurveyDTO surveyDTO = modelMapper.getTypeMap(Survey.class, SurveyDTO.class).map(survey);
		modelMapper.getTypeMap(User.class, SurveyDTO.class).map(survey.getUser(), surveyDTO);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString(),
				survey.getCreationDateFormat());
		Set<SurveyQuestionsDTO> surveyQuestionsDTOs = new HashSet<>();
		for (SurveyQuestions question : survey.getSurveyQuestions()) {
			SurveyQuestionsDTO surveyQuestionsDTO = modelMapper
					.getTypeMap(SurveyQuestions.class, SurveyQuestionsDTO.class).map(question);
			surveyQuestionsDTOs.add(surveyQuestionsDTO);
		}
		surveyDTO.setQuestions(surveyQuestionsDTOs);
		return surveyDTO;
	}

	/**
	 * Conversion of an instance of Survey to an instance of SurveyDTO skipping
	 * QuestionsList
	 * 
	 * @param survey an instance of Survey
	 * @return an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	public SurveyDTO convertToDTOAndSkipQuestions(Survey survey) {

		SurveyDTO surveyDTO = modelMapper.getTypeMap(Survey.class, SurveyDTO.class).map(survey);
		modelMapper.getTypeMap(User.class, SurveyDTO.class).map(survey.getUser(), surveyDTO);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString(),
				survey.getCreationDateFormat());
		return surveyDTO;
	}

	/**
	 * Conversion of an instance of SurveyDTO to an instance of Survey
	 * 
	 * @param surveyDTO an instance of SurveyDTO
	 * @return an instance of Survey
	 * @throws FormatException
	 * @throws EmptyFieldException
	 * @see DTOMapping#convertToEntity
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@Override
	public Survey convertToEntity(SurveyDTO surveyDTO) throws FormatException, NotFoundException, EmptyFieldException {
		Survey survey = modelMapper.getTypeMap(SurveyDTO.class, Survey.class).map(surveyDTO);
		survey.setUser(userRepository.get(surveyDTO.getUserDTO().getId()));
		Date creationDate = new Date();
		surveyDTO.setCreationDate(survey.getCreationDateFormat().format(creationDate));
		survey.setCreationDate(
				surveyDTO.getCreationDateConverted(TimeZone.getDefault().toString(), survey.getCreationDateFormat()));
		survey.setName(surveyDTO.getSurveyName());
		Set<SurveyQuestions> surveyQuestions = new HashSet<>();
		if (surveyDTO.getQuestions() != null) {
			Set<SurveyQuestionsDTO> surveyQuestionsDTO = surveyDTO.getQuestions();
			for (SurveyQuestionsDTO surveyQuestionDTO : surveyQuestionsDTO) {
				surveyQuestions.add(
						modelMapper.getTypeMap(SurveyQuestionsDTO.class, SurveyQuestions.class).map(surveyQuestionDTO));
			}
		}
		survey.setSurveyQuestions(surveyQuestions);
		return survey;
	}
}
