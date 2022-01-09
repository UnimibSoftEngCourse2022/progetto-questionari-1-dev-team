package it.unimib.unimibmodules.controller;

import java.text.ParseException;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unimib.unimibmodules.dto.SurveyDTO;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.repository.SurveyRepository;

/**
 * Controller handling HTTP requests related to Survey.
 * @author Luca Milazzo
 */
@RestController
@RequestMapping("/api")
public class SurveyController extends DTOMapping<Survey, SurveyDTO>{
	
	/**
	 * Instance of SurveyRepository that will be used to access Repository layer.
	 */
	private final SurveyRepository surveyRepository;

	/**
	 * The instance of modelMapper that will be used to convert Survey to SurveyDTO and vice versa.
	 */
	private final ModelMapper modelMapper;
	
	
	@Autowired
	public SurveyController(SurveyRepository surveyRepository, ModelMapper modelMapper) {

		this.surveyRepository = surveyRepository;
		this.modelMapper = modelMapper;
	}
	
	
		/**
	 * Gets the survey associated with the given id.
	 * @param	id	the id of the Survey
	 * @return		an HTTP response with status 200 and the SurveyDTO if the survey has been found, 500 otherwise
	 */
	@GetMapping(path = "/getSurvey/{id}")
	public ResponseEntity<Survey> getSurvey(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Creates a survey, with the given name and request's date-time, that is associated to the given user id
	 * @param	name	the name of the survey
	 * @param	userId
	 * @return			an HTTP response with status 200 if the survey has been added, 500 otherwise
	 */
	@PostMapping(path = "/postSurvey")
	public ResponseEntity<Survey> postSurvey(@RequestParam String name, @RequestParam int userId) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Modifies the survey's name associated with the given id.
	 * @param	id		the id of the survey
	 * @param	name	the new name of the answer
	 * @return			an HTTP response with status 200 if the survey has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/patchSurvey")
	public ResponseEntity<Survey> patchSurvey(@RequestParam int id, @RequestParam String name) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	
	/**
	 * Deletes the survey associated with the given id.
	 * @param   id	the id of the survey that will be deleted
	 * @return		an HTTP Response with status 200 if the survey has been deleted, 500 otherwise
	 */
	@DeleteMapping(path = "/deleteSurvey/{id}")
	public ResponseEntity<Survey> deleteSurvey(@PathVariable int id) {

		// TODO Auto-generated method stub
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
	
	/**
	 * Converts an instance of Survey to an instance of SurveyDTO
	 * @param   survey	an instance of Survey
	 * @return			an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public SurveyDTO convertToDTO(Survey survey) {
		SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString());
	    return surveyDTO;
	}

	/**
	 * Converts an instance of SurveyDTO to an instance of Survey
	 * @param   surveyDTO	an instance of SurveyDTO
	 * @return				an instance of Survey, containing the deserialized data of surveyDTO
	 * @throws ParseException 
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Survey convertToEntity(SurveyDTO surveyDTO) throws ParseException {

		Survey survey = modelMapper.map(surveyDTO, Survey.class);
		survey.setCreationDate(surveyDTO.getCreationDateConverted(TimeZone.getDefault().toString()));
	    return survey;
	}

}