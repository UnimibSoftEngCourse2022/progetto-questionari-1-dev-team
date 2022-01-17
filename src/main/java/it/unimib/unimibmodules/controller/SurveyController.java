package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.dto.SurveyDTO;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller handling HTTP requests related to Survey.
 * @author Luca Milazzo
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api")
public class SurveyController extends DTOMapping<Survey, SurveyDTO>{
	/**
	 * Instance of SurveyRepository. It's used to access the Repository layer.
	 */
	private final SurveyRepository surveyRepository;
	private static final Logger logger = LogManager.getLogger(Survey.class);
	
	@Autowired
	public SurveyController(SurveyRepository surveyRepository, ModelMapper modelMapper) {
		
		super(modelMapper);
		this.surveyRepository = surveyRepository;
	}
	
	/**
	 * Finds the survey associated with the given id.
	 * @param	id	the id of the Survey
	 * @return		an HTTP response with status 200 and the SurveyDTO if the survey has been found
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurvey")
	public ResponseEntity<SurveyDTO> findSurvey(@RequestParam(name = "id") int id) throws NotFoundException {
		
		Survey survey = surveyRepository.get(id);
		logger.debug("Retrieved Survey with id "+ id + ".");
		return new ResponseEntity<>(convertToDTO(survey), HttpStatus.OK);
	}
	
	/**
	 * Finds the survey associated with the given id, without its questions list .
	 * @param	id	the id of the Survey
	 * @return		an HTTP response with status 200 and the SurveyDTO if the survey has been found
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findSurveyNoQuestions")
	public ResponseEntity<SurveyDTO> findSurveyNoQuestions(@RequestParam(name = "id") int id) throws NotFoundException {
		
		Survey survey = surveyRepository.get(id);
		logger.debug("Retrieved Survey without questions with id "+ id + ".");
		return new ResponseEntity<>(convertToDTOAndSkipQuestions(survey), HttpStatus.OK);
	}
	
	/**
	 * Finds all surveys, without their questions list.
	 * @return		an HTTP response with status 200 if all surveys have been found.
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@GetMapping("/findAllSurveys")
	public ResponseEntity<List<SurveyDTO>> findAllSurveys() throws NotFoundException{
		
		Iterable<Survey> surveys = surveyRepository.getAll();
		logger.debug("Retrieved all Surveys.");
		List<SurveyDTO> surveysDTO = new ArrayList<>();
		for( Survey survey : surveys ){
			surveysDTO.add(convertToDTO(survey));
		}
		return new ResponseEntity<>(surveysDTO, HttpStatus.OK);
	}
	
	/**
	 * Creates a survey, with the given name and request's date-time, that is associated to the given user id
	 * @param	surveyDTO	the serialized version of a Survey object
	 * @return			an HTTP response with status 201 if the survey has been added
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@PostMapping("/createSurvey")
	public ResponseEntity<String> postSurvey(@RequestParam SurveyDTO surveyDTO) throws FormatException {
		
		Survey survey = convertToEntity(surveyDTO);
		surveyRepository.add(survey);
		logger.debug("Added Survey with id +" + survey.getId() + ".");
		return new ResponseEntity<>("Survey created.", HttpStatus.CREATED);
	}
	
	/**
	 * Modifies the survey's name associated with the given surveyDTO.
	 * @param	surveyDTO	the serialized version of a Survey object
	 * @return			an HTTP response with status 200 if the survey has been updated
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@PatchMapping(path = "/modifySurvey")
	public ResponseEntity<String> patchSurvey(@RequestParam SurveyDTO surveyDTO) throws FormatException {
		
		Survey survey = convertToEntity(surveyDTO);
		surveyRepository.modify(survey);
		logger.debug("Updated Survey with id "+ survey.getId() + ".");
		return new ResponseEntity<>("Survey updated.", HttpStatus.OK);
	}
	
	
	/**
	 * Deletes the survey associated with the given id.
	 * @param   id	the id of the survey to delete
	 * @return		an HTTP Response with status 200 if the survey has been deleted
	 * @throws NotFoundException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 */
	@DeleteMapping(path = "/deleteSurvey")
	public ResponseEntity<String> deleteSurvey(@RequestParam(name = "id") int id) throws NotFoundException{
		
		surveyRepository.remove(id);
		logger.debug("Removed Survey with id +" + id+ ".");
		return new ResponseEntity<>("Survey deleted", HttpStatus.OK);
	}
	
	/**
	 * Conversion of an instance of Survey to an instance of SurveyDTO
	 * @param   survey	an instance of Survey
	 * @return			an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public SurveyDTO convertToDTO(Survey survey) {
		
		SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString(), survey.getCreationDateFormat());
	    return surveyDTO;
	}
	
	/**
	 * Conversion of an instance of Survey to an instance of SurveyDTO skipping QuestionsList
	 * @param   survey	an instance of Survey
	 * @return			an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	public SurveyDTO convertToDTOAndSkipQuestions(Survey survey) {
		
		TypeMap<Survey, SurveyDTO> propertyMapper = modelMapper.createTypeMap(Survey.class, SurveyDTO.class);
	    propertyMapper.addMappings(modelMapper -> modelMapper.skip(SurveyDTO::setQuestions));
	    
		SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString(), survey.getCreationDateFormat());
	    return surveyDTO;
	}

	/**
	 * Conversion of an instance of SurveyDTO to an instance of Survey
	 * @param   surveyDTO	an instance of SurveyDTO
	 * @return				an instance of Survey
	 * @throws FormatException
	 * @see DTOMapping#convertToEntity
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@Override
	public Survey convertToEntity(SurveyDTO surveyDTO) throws FormatException {
		
		Survey survey = modelMapper.map(surveyDTO, Survey.class);
		survey.setCreationDate(surveyDTO.getCreationDateConverted(TimeZone.getDefault().toString(), survey.getCreationDateFormat()));
	    return survey;
	}
}